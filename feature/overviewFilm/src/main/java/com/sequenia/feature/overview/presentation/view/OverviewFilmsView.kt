package com.sequenia.feature.overview.presentation.view

import com.sequenia.feature.overview.domain.entity.FilmPreview
import com.sequenia.feature.overview.presentation.model.GenreUi
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface OverviewFilmsView : MvpView {

	fun loading()
	fun error()
	fun error(msg: String)
	fun content(genre: List<GenreUi>, films: List<FilmPreview>)
}