package com.sequenia.feature.detailsfilm.presentation.presenter

import com.sequenia.feature.detailsfilm.domain.entity.Film
import com.sequenia.feature.detailsfilm.domain.usecase.GetFilmUseCase
import com.sequenia.feature.detailsfilm.presentation.router.FilmDetailsRouter
import com.sequenia.feature.detailsfilm.presentation.view.FilmDetailsView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class FilmDetailsPresenter(
	private val router: FilmDetailsRouter,
	private val getFilmUseCase: GetFilmUseCase
) : MvpPresenter<FilmDetailsView>() {

	lateinit var film: Film

	private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
		viewState.error()
	}

	fun init(filmId: Long) {
		loadFilm(filmId)
	}

	private fun loadFilm(filmId: Long) {
		viewState.loading()
		presenterScope.launch(coroutineExceptionHandler) {
			launch {
				film = getFilmUseCase(filmId)

				viewState.content(film)
			}
		}
	}

	fun navigateBack() {
		router.navigateBack()
	}
}