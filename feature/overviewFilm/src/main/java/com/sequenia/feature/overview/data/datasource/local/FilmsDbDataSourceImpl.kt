package com.sequenia.feature.overview.data.datasource.local

import com.sequenia.shared.filmsdatabase.dao.FilmsDao
import com.sequenia.shared.filmsdatabase.dto.FilmDto
import com.sequenia.shared.filmsdatabase.dto.FilmPreviewDto
import com.sequenia.shared.filmsdatabase.dto.FilmWithGenreDto

class FilmsDbDataSourceImpl(
	private val dao: FilmsDao
) : FilmsDbDataSource {

	override suspend fun get(): List<FilmPreviewDto> =
		dao.getPreviewFilms()

	override suspend fun get(filter: Long): FilmWithGenreDto =
		dao.getAllFilmsWithGenreFilter(filter)

	override suspend fun insert(films: List<FilmDto>) {
		dao.insert(films)
	}
}