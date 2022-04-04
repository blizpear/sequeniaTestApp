package com.sequenia.feature.overview.data.models

import com.squareup.moshi.Json

data class FilmRemoteInnerModel(
	val id: Long,
	@Json(name = "localized_name")
	val localizedName: String,
	val name: String,
	val year: Int,
	val rating: Double?,
	@Json(name = "image_url")
	val imageUrl: String?,
	val description: String?,
	val genres: List<String>
)