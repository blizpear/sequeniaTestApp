package com.sequenia.feature.overview.data.datasource.remote

import com.sequenia.feature.overview.data.api.FilmsApi
import com.sequenia.feature.overview.data.models.FilmRemoteModel

class FilmsRemoteDataSourceImpl(
	private val api: FilmsApi
) : FilmsRemoteDataSource {

	override suspend fun getFilms(): FilmRemoteModel =
		api.getFilms()
}