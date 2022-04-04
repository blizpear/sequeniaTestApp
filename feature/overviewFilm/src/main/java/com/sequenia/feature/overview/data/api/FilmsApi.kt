package com.sequenia.feature.overview.data.api

import com.sequenia.feature.overview.data.models.FilmRemoteModel
import retrofit2.http.GET

interface FilmsApi {

	@GET("films.json")
	suspend fun getFilms(): FilmRemoteModel
}