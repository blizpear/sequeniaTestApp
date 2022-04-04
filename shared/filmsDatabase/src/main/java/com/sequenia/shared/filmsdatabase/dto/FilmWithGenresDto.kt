package com.sequenia.shared.filmsdatabase.dto

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class FilmWithGenresDto(
	@Embedded
	val film: FilmDto,
	@Relation(
		parentColumn = "filmId",
		entityColumn = "genreId",
		associateBy = Junction(FilmGenreCrossRefDto::class)
	)
	val genres: List<GenreDto>
)