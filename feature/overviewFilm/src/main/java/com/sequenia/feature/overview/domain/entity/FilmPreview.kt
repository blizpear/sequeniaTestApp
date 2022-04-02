package com.sequenia.feature.overview.domain.entity

data class FilmPreview(
	val filmId: Long,
	val localizedName: String,
	val imageUrl: String?
)