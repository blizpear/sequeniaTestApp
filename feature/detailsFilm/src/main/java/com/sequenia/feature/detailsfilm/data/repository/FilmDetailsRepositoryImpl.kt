package com.sequenia.feature.detailsfilm.data.repository

import com.sequenia.feature.detailsfilm.data.datasource.FilmDetailsDataSource
import com.sequenia.feature.detailsfilm.data.mappers.toEntity
import com.sequenia.feature.detailsfilm.domain.entity.Film
import com.sequenia.feature.detailsfilm.domain.repository.FilmDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmDetailsRepositoryImpl(
	private val filmDetailsDataSource: FilmDetailsDataSource
) : FilmDetailsRepository {

	override suspend fun getFilm(filmId: Long): Film =
		withContext(Dispatchers.IO) {
			filmDetailsDataSource.getFilm(filmId).toEntity()
		}
}