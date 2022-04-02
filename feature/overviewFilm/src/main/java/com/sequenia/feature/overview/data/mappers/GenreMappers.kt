package com.sequenia.feature.overview.data.mappers

import com.sequenia.feature.overview.domain.entity.Genre
import com.sequenia.shared.filmsdatabase.dto.GenreDto

fun GenreDto.toEntity() = Genre(genreId = genreId, genre = genre)
fun List<GenreDto>.toEntityList() = map(GenreDto::toEntity)