package com.sequenia.feature.overview.data.datasource.local

import com.sequenia.shared.filmsdatabase.dao.GenreDao
import com.sequenia.shared.filmsdatabase.dto.GenreDto

class GenreDbDataSourceImpl(
	private val dao: GenreDao
) : GenreDbDataSource {

	override suspend fun get(): List<GenreDto> =
		dao.getUniqueGenres()

	override suspend fun insert(genres: List<GenreDto>) {
		dao.insert(genres)
	}
}