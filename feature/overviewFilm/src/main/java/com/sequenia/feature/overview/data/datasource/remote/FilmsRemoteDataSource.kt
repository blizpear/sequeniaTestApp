package com.sequenia.feature.overview.data.datasource.remote

import com.sequenia.feature.overview.data.models.FilmRemoteModel

interface FilmsRemoteDataSource {

	suspend fun getFilms(): FilmRemoteModel
}