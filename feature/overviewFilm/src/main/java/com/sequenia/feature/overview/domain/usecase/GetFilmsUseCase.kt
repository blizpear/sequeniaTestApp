package com.sequenia.feature.overview.domain.usecase

import com.sequenia.feature.overview.domain.entity.FilmPreview
import com.sequenia.feature.overview.domain.repository.OverviewFilmRepository

class GetFilmsUseCase(private val repository: OverviewFilmRepository) {

	suspend operator fun invoke(filter: Long?): List<FilmPreview> =
		repository.getFilms(filter)
}