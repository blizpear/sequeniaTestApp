package com.sequenia.feature.detailsfilm.domain.repository

import com.sequenia.feature.detailsfilm.domain.entity.Film

interface FilmDetailsRepository {

	suspend fun getFilm(filmId: Long): Film
}