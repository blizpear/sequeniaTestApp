package com.sequenia.shared.filmsdatabase.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films_table")
data class FilmDto(
	@PrimaryKey(autoGenerate = false)
	val filmId: Long,
	val localizedName: String,
	val name: String,
	val year: Int,
	val imageUrl: String,
	val description: String
)