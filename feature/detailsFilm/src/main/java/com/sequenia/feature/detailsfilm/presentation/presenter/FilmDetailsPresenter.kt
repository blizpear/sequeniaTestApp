package com.sequenia.feature.detailsfilm.presentation.presenter

import com.sequenia.feature.detailsfilm.domain.entity.Film
import com.sequenia.feature.detailsfilm.domain.usecase.GetFilmUseCase
import com.sequenia.feature.detailsfilm.presentation.router.FilmDetailsRouter
import com.sequenia.feature.detailsfilm.presentation.view.FilmDetailsView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class FilmDetailsPresenter(
	private val router: FilmDetailsRouter,
	private val getFilmUseCase: GetFilmUseCase
) : MvpPresenter<FilmDetailsView>() {

	lateinit var film: Film

	private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable: Throwable ->
		throwable.localizedMessage.takeIf {
			!it.isNullOrBlank()
		}?.let { viewState.error(it) } ?: viewState.error()
	}

	fun init(filmId: Long) {
		viewState.loading()
		presenterScope.launch(coroutineExceptionHandler) {
			loadFilm(filmId)
			viewState.content(film)
		}
	}

	private suspend fun loadFilm(filmId: Long) = coroutineScope {
		viewState.loading()
		launch {
			film = getFilmUseCase(filmId)
		}.join()
	}

	fun navigateBack() {
		router.navigateBack()
	}
}