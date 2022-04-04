package com.sequenia.feature.overview.presentation.ui.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sequenia.feature.overview.domain.entity.FilmPreview
import com.sequenia.feature.overview.presentation.model.FilmOverviewModel
import com.sequenia.feature.overview.presentation.model.GenreUi
import com.sequenia.feature.overview.presentation.model.ItemViewType
import com.sequenia.feature.overview.presentation.ui.viewholders.FilmsViewHolder
import com.sequenia.feature.overview.presentation.ui.viewholders.GenreViewHolder
import com.sequenia.feature.overview.presentation.ui.viewholders.HeaderViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilmsAdapter(
	private val onGenreClickAction: (Long) -> Unit,
	private val onFilmClickAction: (film: FilmPreview) -> Unit,
	private val loadImageAction: (view: ImageView, url: String?) -> Unit
) : ListAdapter<FilmOverviewModel, RecyclerView.ViewHolder>(FilmDiffCallback) {

	private val coroutineScope = CoroutineScope(Job() + Dispatchers.Default)

	override fun getItemViewType(position: Int): Int {
		return getItem(position).viewType
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
		when (viewType) {
			ItemViewType.HEADER.type ->
				HeaderViewHolder.from(parent)

			ItemViewType.FILM.type   ->
				FilmsViewHolder.from(parent)

			ItemViewType.GENRE.type  ->
				GenreViewHolder.from(parent)

			else                     -> throw IllegalStateException("Unresolved type of ViewHolder")
		}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		when (holder) {
			is FilmsViewHolder  -> holder.bind(
				(getItem(position) as FilmOverviewModel.Film).film,
				onFilmClickAction,
				loadImageAction
			)

			is HeaderViewHolder -> holder.bind(
				(getItem(position) as FilmOverviewModel.Header).name
			)

			is GenreViewHolder  -> holder.bind(
				(getItem(position) as FilmOverviewModel.Genre).genreUi,
				onGenreClickAction
			)
		}
	}

	fun submitData(
		filmsHeader: String,
		films: List<FilmPreview>,
		genresHeader: String,
		genres: List<GenreUi>
	) {
		coroutineScope.launch {
			val newList = mutableListOf<FilmOverviewModel>().apply {
				add(FilmOverviewModel.Header(genresHeader))
				addAll(genres.map { FilmOverviewModel.Genre(it) })
				add(FilmOverviewModel.Header(filmsHeader))
				addAll(films.map { FilmOverviewModel.Film(it) })
			}
			withContext(Dispatchers.Main) {
				submitList(newList)
			}
		}
	}

	override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
		coroutineScope.cancel()
		super.onDetachedFromRecyclerView(recyclerView)
	}
}

object FilmDiffCallback : DiffUtil.ItemCallback<FilmOverviewModel>() {

	override fun areItemsTheSame(
		oldItem: FilmOverviewModel,
		newItem: FilmOverviewModel
	): Boolean {
		var result = true

		if (oldItem is FilmOverviewModel.Film && newItem is FilmOverviewModel.Film) {
			result = oldItem.film.filmId == newItem.film.filmId
		} else if (oldItem is FilmOverviewModel.Genre && newItem is FilmOverviewModel.Genre) {
			result = oldItem.genreUi.genre.genreId == newItem.genreUi.genre.genreId
		}

		return result
	}

	override fun areContentsTheSame(
		oldItem: FilmOverviewModel,
		newItem: FilmOverviewModel
	): Boolean = oldItem == newItem
}