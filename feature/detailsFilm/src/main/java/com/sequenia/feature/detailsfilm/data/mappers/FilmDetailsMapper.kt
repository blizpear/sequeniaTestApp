package com.sequenia.feature.detailsfilm.data.mappers

import com.sequenia.feature.detailsfilm.domain.entity.Film
import com.sequenia.shared.filmsdatabase.dto.FilmWithGenresDto

fun FilmWithGenresDto.toEntity() = with(film) {
	Film(
		filmId = filmId,
		localizedName = localizedName,
		name = name,
		year = year.toString(),
		rating = rating.toString(),
		imageUrl = imageUrl,
		description = description,
		genres = genres.map { it.genre }
	)
}