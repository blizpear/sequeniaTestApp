package com.sequenia.feature.overview.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sequenia.component.ui.animation.hideWithFade
import com.sequenia.component.ui.animation.showWithFade
import com.sequenia.component.ui.fragments.BaseFragment
import com.sequenia.feature.overview.R
import com.sequenia.feature.overview.databinding.OverviewFilmsFragmentBinding
import com.sequenia.feature.overview.domain.entity.FilmPreview
import com.sequenia.feature.overview.presentation.model.GenreUi
import com.sequenia.feature.overview.presentation.model.ItemViewType
import com.sequenia.feature.overview.presentation.presenter.OverviewFilmsPresenter
import com.sequenia.feature.overview.presentation.ui.adapter.FilmsAdapter
import com.sequenia.feature.overview.presentation.view.OverviewFilmsView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get

class OverviewFilmsFragment : BaseFragment<OverviewFilmsFragmentBinding>(), OverviewFilmsView {

	companion object {

		fun getInstance() = OverviewFilmsFragment()

		const val ONE_ELEMENT_IN_ROW = 2
		const val TWO_ELEMENT_IN_ROW = 1
	}

	private lateinit var filmsAdapter: FilmsAdapter

	@InjectPresenter
	lateinit var presenter: OverviewFilmsPresenter

	@ProvidePresenter
	fun provide(): OverviewFilmsPresenter = get()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setAdapter()
	}

	private fun setAdapter() {
		filmsAdapter = FilmsAdapter(::onGenreClicked, ::onFilmClicked, ::loadImageForItem)
		binding.content.adapter = filmsAdapter

		val manager = GridLayoutManager(requireContext(), 2)
		manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
			override fun getSpanSize(position: Int): Int {
				return when (val viewType = filmsAdapter.getItemViewType(position)) {
					ItemViewType.HEADER.type -> ONE_ELEMENT_IN_ROW
					ItemViewType.GENRE.type  -> ONE_ELEMENT_IN_ROW
					ItemViewType.FILM.type   -> TWO_ELEMENT_IN_ROW
					else                     -> throw IllegalStateException("Unknown viewType $viewType")
				}
			}
		}

		binding.content.layoutManager = manager
	}

	private fun onGenreClicked(genreId: Long) {
		presenter.setFilter(genreId)
	}

	private fun onFilmClicked(filmId: Long) {
		presenter.navigateToDetailsScreen(filmId)
	}

	private fun loadImageForItem(view: ImageView, url: String?) {
		if (url != null) {
			Glide.with(this)
				.load(url)
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.placeholder(com.sequenia.component.ui.R.drawable.ic_placeholder)
				.into(view)
		} else {
			view.setImageDrawable(
				ContextCompat.getDrawable(
					requireContext(),
					com.sequenia.component.ui.R.drawable.ic_placeholder
				)
			)
		}
	}

	override fun loading() {
		binding.error.hideWithFade()
		binding.content.hideWithFade()

		binding.progressBar.showWithFade()
	}

	override fun error() {
		binding.content.hideWithFade()
		binding.progressBar.hideWithFade()

		binding.error.showWithFade()
	}

	override fun error(msg: String) {
		binding.content.hideWithFade()
		binding.progressBar.hideWithFade()

		binding.error.showWithFade()

		binding.errorText.text = msg
	}

	override fun content(genre: List<GenreUi>, films: List<FilmPreview>) {
		binding.error.hideWithFade()
		binding.progressBar.hideWithFade()

		binding.content.showWithFade()

		filmsAdapter.submitData(
			getString(R.string.overview_film_films),
			films,
			getString(R.string.overview_film_genre),
			genre
		)
	}

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): OverviewFilmsFragmentBinding =
		OverviewFilmsFragmentBinding.inflate(inflater, container, false)
}