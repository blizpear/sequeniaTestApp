package com.sequenia.shared.filmsdatabase.dto

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
	tableName = "film_genre_cross_ref",
	primaryKeys = ["filmId", "genreId"]
)
data class FilmGenreCrossRefDto(
	val filmId: Long,
	@ColumnInfo(index = true)
	val genreId: Long
)