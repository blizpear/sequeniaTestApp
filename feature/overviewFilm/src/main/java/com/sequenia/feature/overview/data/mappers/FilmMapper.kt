package com.sequenia.feature.overview.data.mappers

import com.sequenia.feature.overview.data.models.FilmRemoteInnerModel
import com.sequenia.feature.overview.domain.entity.FilmPreview
import com.sequenia.shared.filmsdatabase.dto.FilmDto
import com.sequenia.shared.filmsdatabase.dto.FilmPreviewDto
import com.sequenia.shared.filmsdatabase.dto.GenreWithFilms

fun FilmRemoteInnerModel.toDatabaseEntity() = FilmDto(
	filmId = id,
	localizedName = localizedName,
	name = name,
	year = year,
	rating = rating,
	imageUrl = imageUrl,
	description = description
)

fun FilmPreviewDto.toEntity() = FilmPreview(
	filmId = filmId,
	localizedName = localizedName,
	imageUrl = imageUrl
)

fun FilmDto.toEntity() = FilmPreview(
	filmId = filmId,
	localizedName = localizedName,
	imageUrl = imageUrl
)

fun List<FilmPreviewDto>.toFilmsEntityList() = map(FilmPreviewDto::toEntity)

fun GenreWithFilms.toEntityList() = films.map(FilmDto::toEntity)