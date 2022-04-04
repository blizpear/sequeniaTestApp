package com.sequenia.feature.detailsfilm.domain.entity

data class Film(
	val filmId: Long,
	val localizedName: String,
	val name: String,
	val year: String,
	val rating: String?,
	val imageUrl: String?,
	val description: String?,
	val genres: List<String>
)