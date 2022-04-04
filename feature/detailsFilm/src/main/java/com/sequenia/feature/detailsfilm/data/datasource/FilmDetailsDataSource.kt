package com.sequenia.feature.detailsfilm.data.datasource

import com.sequenia.shared.filmsdatabase.dto.FilmWithGenresDto

interface FilmDetailsDataSource {

	suspend fun getFilm(filmId: Long): FilmWithGenresDto
}