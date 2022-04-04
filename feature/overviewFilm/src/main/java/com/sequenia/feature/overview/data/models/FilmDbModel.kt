package com.sequenia.feature.overview.data.models

import com.sequenia.shared.filmsdatabase.dto.FilmDto
import com.sequenia.shared.filmsdatabase.dto.FilmGenreCrossRefDto
import com.sequenia.shared.filmsdatabase.dto.GenreDto

data class FilmDbModel(
	val films: List<FilmDto>,
	val genres: List<GenreDto>,
	val crossRef: List<FilmGenreCrossRefDto>
)