package com.sequenia.shared.filmsdatabase.dto

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

class FilmWithGenreDto(
	@Embedded
	var film: FilmDto,
	@Relation(
		parentColumn = "filmId",
		entityColumn = "genreId",
		associateBy = Junction(FilmGenreCrossRef::class)
	)
	var genre: List<GenreDto>
)