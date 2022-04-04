package com.sequenia.feature.overview.data.datasource.local

import com.sequenia.shared.filmsdatabase.dto.FilmDto
import com.sequenia.shared.filmsdatabase.dto.FilmPreviewDto
import com.sequenia.shared.filmsdatabase.dto.GenreWithFilms

interface FilmsDbDataSource {

	suspend fun get(): List<FilmPreviewDto>

	suspend fun get(filter: Long): GenreWithFilms

	suspend fun insert(films: List<FilmDto>)
}