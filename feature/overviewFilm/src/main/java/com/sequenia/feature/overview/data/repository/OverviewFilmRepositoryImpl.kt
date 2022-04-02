package com.sequenia.feature.overview.data.repository

import com.sequenia.feature.overview.data.datasource.local.FilmGenreCrossRefDataSource
import com.sequenia.feature.overview.data.datasource.local.FilmsDbDataSource
import com.sequenia.feature.overview.data.datasource.local.GenreDbDataSource
import com.sequenia.feature.overview.data.datasource.remote.FilmsRemoteDataSource
import com.sequenia.feature.overview.data.mappers.filmWithGenreMap
import com.sequenia.feature.overview.data.mappers.toEntityList
import com.sequenia.feature.overview.domain.entity.FilmPreview
import com.sequenia.feature.overview.domain.entity.Genre
import com.sequenia.feature.overview.domain.repository.OverviewFilmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OverviewFilmRepositoryImpl(
	private val filmsRemoteDataSource: FilmsRemoteDataSource,
	private val filmsDbDataSource: FilmsDbDataSource,
	private val genreDbDataSource: GenreDbDataSource,
	private val filmGenreCrossRefDataSource: FilmGenreCrossRefDataSource
) : OverviewFilmRepository {

	override suspend fun updateLocalDb() {
		withContext(Dispatchers.IO) {
			val filmsWithGenre = filmWithGenreMap(
				filmsRemoteDataSource.getFilms()
			)
			launch {
				filmsDbDataSource.insert(
					filmsWithGenre.films
				)
			}
			launch {
				filmGenreCrossRefDataSource.insert(
					filmsWithGenre.crossRef
				)
			}
			launch {
				genreDbDataSource.insert(
					filmsWithGenre.genres
				)
			}
		}
	}

	override suspend fun getFilms(filter: Long?): List<FilmPreview> =
		withContext(Dispatchers.IO) {
			if (filter != null) {
				filmsDbDataSource.get(filter).toEntityList().sortedBy { it.localizedName }
			} else {
				filmsDbDataSource.get().toEntityList().sortedBy { it.localizedName }
			}
		}

	override suspend fun getGenres(): List<Genre> =
		withContext(Dispatchers.IO) {
			genreDbDataSource.get().toEntityList()
		}
}