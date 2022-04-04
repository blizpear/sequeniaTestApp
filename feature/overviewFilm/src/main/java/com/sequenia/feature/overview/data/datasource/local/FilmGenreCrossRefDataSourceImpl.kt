package com.sequenia.feature.overview.data.datasource.local

import com.sequenia.shared.filmsdatabase.dao.FilmGenreCrossRefDao
import com.sequenia.shared.filmsdatabase.dto.FilmGenreCrossRefDto

class FilmGenreCrossRefDataSourceImpl(
	private val dao: FilmGenreCrossRefDao
) : FilmGenreCrossRefDataSource {

	override suspend fun insert(crossRef: List<FilmGenreCrossRefDto>) {
		dao.insert(crossRef)
	}
}