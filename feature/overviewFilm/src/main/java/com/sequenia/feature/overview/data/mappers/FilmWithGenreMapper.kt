package com.sequenia.feature.overview.data.mappers

import com.sequenia.feature.overview.data.models.FilmDbModel
import com.sequenia.feature.overview.data.models.FilmRemoteModel
import com.sequenia.shared.filmsdatabase.dto.FilmDto
import com.sequenia.shared.filmsdatabase.dto.FilmGenreCrossRefDto
import com.sequenia.shared.filmsdatabase.dto.GenreDto

fun filmWithGenreMap(data: FilmRemoteModel): FilmDbModel {
	val crossRef = mutableListOf<FilmGenreCrossRefDto>()
	val films = mutableListOf<FilmDto>()
	val genres = HashMap<String, Long>()
	var index: Long = 0

	data.films.forEach { filmRemoteInnerModel ->
		films.add(filmRemoteInnerModel.toDatabaseEntity())

		filmRemoteInnerModel.genres.forEach { genre ->
			var key = ++index
			key = genres.getOrPut(genre) { key }

			crossRef.add(FilmGenreCrossRefDto(filmRemoteInnerModel.id, key))
		}
	}

	return FilmDbModel(
		films = films,
		genres = genres.map { GenreDto(genreId = it.value, genre = it.key) },
		crossRef = crossRef
	)
}