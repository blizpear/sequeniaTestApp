package com.sequenia.feature.overview.presentation.presenter

import com.sequenia.feature.overview.domain.entity.FilmPreview
import com.sequenia.feature.overview.domain.usecase.GetFilmsUseCase
import com.sequenia.feature.overview.domain.usecase.GetGenresUseCase
import com.sequenia.feature.overview.domain.usecase.UpdateLocalDbUseCase
import com.sequenia.feature.overview.presentation.model.GenreUi
import com.sequenia.feature.overview.presentation.router.OverviewFilmsRouter
import com.sequenia.feature.overview.presentation.view.OverviewFilmsView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class OverviewFilmsPresenter(
	private val router: OverviewFilmsRouter,
	private val getFilmsUseCase: GetFilmsUseCase,
	private val getGenresUseCase: GetGenresUseCase,
	private val updateLocalDbUseCase: UpdateLocalDbUseCase
) : MvpPresenter<OverviewFilmsView>() {

	private var films: List<FilmPreview> = listOf()
	private var genres: List<GenreUi> = listOf()

	private var selectedGenreId: Long = -1

	private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable: Throwable ->
		throwable.localizedMessage.takeIf {
			!it.isNullOrBlank()
		}?.let { viewState.error(it) } ?: viewState.error()
	}

	init {
		viewState.loading()
		presenterScope.launch(coroutineExceptionHandler) {
			loadAndUpdateLocalData()
			getGenresAndFilms()
		}
	}

	private suspend fun loadAndUpdateLocalData() {
		updateLocalDbUseCase()
	}

	private suspend fun getGenresAndFilms(filter: Long? = null) = coroutineScope {
		viewState.loading()
		launch {
			launch {
				genres = getGenresUseCase().map { genre ->
					GenreUi(genre = genre, isSelected = filter == genre.genreId)
				}
			}
			launch {
				films = getFilmsUseCase(filter)
			}
		}.join()

		viewState.content(genre = genres, films = films)
	}

	fun setFilter(filter: Long) {
		presenterScope.launch {
			selectedGenreId = if (filter == selectedGenreId) {
				getGenresAndFilms()
				-1
			} else {
				getGenresAndFilms(filter)
				filter
			}
		}
	}

	fun clickOnErrorButton() {
		viewState.loading()
		presenterScope.launch(coroutineExceptionHandler) {
			loadAndUpdateLocalData()
			getGenresAndFilms()
		}
	}

	fun navigateToDetailsScreen(filmId: Long) {
		router.navigateToDetailsFilmScreen(filmId)
	}
}