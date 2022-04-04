package com.sequenia.shared.filmsdatabase.dto

import androidx.room.Junction
import androidx.room.Relation

data class GenreWithFilms(
	val genreId: Long,
	@Relation(
		parentColumn = "genreId",
		entityColumn = "filmId",
		associateBy = Junction(FilmGenreCrossRefDto::class)
	)
	val films: List<FilmDto>
)