package com.sequenia.feature.overview.domain.usecase

import com.sequenia.feature.overview.domain.repository.OverviewFilmRepository

class GetGenresUseCase(private val repository: OverviewFilmRepository) {

	suspend operator fun invoke() = repository.getGenres()
}