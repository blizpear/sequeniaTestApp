package com.sequenia.feature.detailsfilm.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sequenia.component.ui.animation.hideWithFade
import com.sequenia.component.ui.animation.showWithFade
import com.sequenia.component.ui.fragments.BaseFragment
import com.sequenia.feature.detailsfilm.R
import com.sequenia.feature.detailsfilm.databinding.FilmDetailsFragmentBinding
import com.sequenia.feature.detailsfilm.domain.entity.Film
import com.sequenia.feature.detailsfilm.presentation.presenter.FilmDetailsPresenter
import com.sequenia.feature.detailsfilm.presentation.view.FilmDetailsView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get

private const val FILM_ID = "FILM_ID"
private var Bundle.filmId: Long
	get() = getLong(FILM_ID)
	set(value) = putLong(FILM_ID, value)

class FilmDetailsFragment : BaseFragment<FilmDetailsFragmentBinding>(), FilmDetailsView {

	companion object {

		fun getInstance(filmId: Long) =
			FilmDetailsFragment().apply {
				val bundle = Bundle()
				bundle.filmId = filmId
				arguments = bundle
			}

		const val ZERO_LONG = 0L
	}

	@InjectPresenter
	lateinit var presenter: FilmDetailsPresenter

	@ProvidePresenter
	fun provide(): FilmDetailsPresenter = get()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initPresenter()
		setListeners()
	}

	private fun initPresenter() {
		presenter.init(filmId = arguments?.filmId ?: ZERO_LONG)
	}

	private fun setListeners() {
		binding.retryButton.setOnClickListener {
			presenter.init(filmId = arguments?.filmId ?: ZERO_LONG)
		}
		binding.backButton.setOnClickListener {
			presenter.navigateBack()
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

	override fun content(film: Film) {
		binding.error.hideWithFade()
		binding.progressBar.hideWithFade()

		binding.content.showWithFade()

		with(binding) {
			titleText.text = film.localizedName
			filmDescription.text = film.description
			filmName.text = film.name
			filmRating.text = getString(R.string.film_details_rating, film.rating)
			filmYear.text = getString(R.string.film_details_year, film.year)
		}
		loadImage(binding.filmImage, film.imageUrl)
	}

	private fun loadImage(view: ImageView, url: String?) {
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

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FilmDetailsFragmentBinding =
		FilmDetailsFragmentBinding.inflate(inflater, container, false)
}