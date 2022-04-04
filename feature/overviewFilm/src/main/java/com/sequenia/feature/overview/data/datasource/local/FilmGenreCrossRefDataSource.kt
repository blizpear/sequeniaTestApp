package com.sequenia.feature.overview.data.datasource.local

import com.sequenia.shared.filmsdatabase.dto.FilmGenreCrossRefDto

interface FilmGenreCrossRefDataSource {

	suspend fun insert(crossRef: List<FilmGenreCrossRefDto>)
}