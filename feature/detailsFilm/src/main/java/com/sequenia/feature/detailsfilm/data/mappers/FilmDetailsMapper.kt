package com.sequenia.feature.detailsfilm.data.mappers

import com.sequenia.feature.detailsfilm.domain.entity.Film
import com.sequenia.shared.filmsdatabase.dto.FilmWithGenresDto

fun FilmWithGenresDto.toEntity() =
	Film(
		filmId = film.filmId,
		localizedName = film.localizedName,
		name = film.name,
		year = film.year.toString(),
		rating = film.rating.toString(),
		imageUrl = film.imageUrl,
		description = film.description,
		genres = genres.map { it.genre }
	)