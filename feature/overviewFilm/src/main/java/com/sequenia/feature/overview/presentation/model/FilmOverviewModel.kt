package com.sequenia.feature.overview.presentation.model

import com.sequenia.feature.overview.domain.entity.FilmPreview

enum class ItemViewType(val type: Int) {
	GENRE(0),
	FILM(1),
	HEADER(2)
}

sealed class FilmOverviewModel {

	abstract val viewType: Int

	data class Genre(val genreUi: GenreUi) : FilmOverviewModel() {

		override val viewType: Int = ItemViewType.GENRE.type
	}

	data class Film(val film: FilmPreview) : FilmOverviewModel() {

		override val viewType: Int = ItemViewType.FILM.type
	}

	data class Header(val name: String) : FilmOverviewModel() {

		override val viewType = ItemViewType.HEADER.type
	}
}