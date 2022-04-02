package com.sequenia.shared.filmsdatabase.dto

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

class FilmWithGenreDto(
	@Embedded
	var film: FilmPreviewDto,
	@Relation(
		parentColumn = "filmId",
		entityColumn = "genreId",
		associateBy = Junction(FilmGenreCrossRefDto::class)
	)
	var genre: List<GenreDto>
)