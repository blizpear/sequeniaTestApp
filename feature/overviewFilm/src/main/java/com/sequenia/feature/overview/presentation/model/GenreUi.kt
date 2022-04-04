package com.sequenia.feature.overview.presentation.model

import com.sequenia.feature.overview.domain.entity.Genre

data class GenreUi(
	val genre: Genre,
	val isSelected: Boolean = false
)