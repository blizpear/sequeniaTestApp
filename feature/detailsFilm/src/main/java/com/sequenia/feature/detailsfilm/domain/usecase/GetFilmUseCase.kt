package com.sequenia.feature.detailsfilm.domain.usecase

import com.sequenia.feature.detailsfilm.domain.repository.FilmDetailsRepository

class GetFilmUseCase(private val repository: FilmDetailsRepository) {

	suspend operator fun invoke(filmId: Long) = repository.getFilm(filmId)
}