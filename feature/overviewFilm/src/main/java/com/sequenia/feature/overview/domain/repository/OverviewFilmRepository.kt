package com.sequenia.feature.overview.domain.repository

import com.sequenia.feature.overview.domain.entity.FilmPreview
import com.sequenia.feature.overview.domain.entity.Genre

interface OverviewFilmRepository {

	suspend fun updateLocalDb()

	suspend fun getFilms(filter: Long?): List<FilmPreview>

	suspend fun getGenres(): List<Genre>
}