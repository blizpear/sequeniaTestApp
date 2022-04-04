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
	}

	private lateinit var filmsAdapter: FilmsAdapter

	@InjectPresenter
	lateinit var presenter: OverviewFilmsPresenter

	@ProvidePresenter
	fun provide(): OverviewFilmsPresenter = get()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		filmsAdapter = FilmsAdapter(::onGenreClicked, ::onFilmClicked, ::loadImageForItem)
		binding.content.adapter = filmsAdapter
	}

	private fun onGenreClicked(genreId: Long) {
		presenter.setFilter(genreId)
	}

	private fun onFilmClicked(film: FilmPreview) {
		presenter.navigateToDetailsScreen(film.filmId)
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
		binding.error.showWithFade()
		binding.content.hideWithFade()
		binding.progressBar.hideWithFade()
	}

	override fun error(msg: String) {
		binding.error.showWithFade()
		binding.content.hideWithFade()
		binding.progressBar.hideWithFade()

		binding.errorText.text = msg
	}

	override fun content(genre: List<GenreUi>, films: List<FilmPreview>) {
		binding.error.hideWithFade()
		binding.content.showWithFade()
		binding.progressBar.hideWithFade()

		filmsAdapter.submitData(
			getString(R.string.overview_film_films),
			films,
			getString(R.string.overview_film_genre),
			genre
		)

		val manager = GridLayoutManager(requireContext(), 2)
		manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
			override fun getSpanSize(position: Int): Int {
				return when (val viewType = filmsAdapter.getItemViewType(position)) {
					ItemViewType.HEADER.type -> 2
					ItemViewType.GENRE.type  -> 2
					ItemViewType.FILM.type   -> 1
					else                     -> throw IllegalStateException("Unknown viewType $viewType")
				}
			}
		}

		binding.content.layoutManager = manager
	}

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): OverviewFilmsFragmentBinding =
		OverviewFilmsFragmentBinding.inflate(inflater, container, false)
}