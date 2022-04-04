package com.sequenia.feature.overview.data.datasource.local

import com.sequenia.shared.filmsdatabase.dto.GenreDto

interface GenreDbDataSource {

	suspend fun get(): List<GenreDto>

	suspend fun insert(genres: List<GenreDto>)
}