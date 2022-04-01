package com.sequenia.shared.filmsdatabase.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre_table")
data class GenreDto(
	@PrimaryKey(autoGenerate = false)
	val genreId: Long,
	val genre: String
)