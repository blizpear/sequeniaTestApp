package com.sequenia.feature.detailsfilm.data.datasource

import com.sequenia.shared.filmsdatabase.dao.FilmsDao
import com.sequenia.shared.filmsdatabase.dto.FilmWithGenresDto

class FilmDetailsDataSourceImpl(
	private val dao: FilmsDao
) : FilmDetailsDataSource {

	override suspend fun getFilm(filmId: Long): FilmWithGenresDto =
		dao.getFilmWithGenre(filmId)
}