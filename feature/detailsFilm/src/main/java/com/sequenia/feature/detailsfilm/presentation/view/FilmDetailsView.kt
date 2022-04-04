package com.sequenia.feature.detailsfilm.presentation.view

import com.sequenia.feature.detailsfilm.domain.entity.Film
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface FilmDetailsView : MvpView {

	fun loading()
	fun error()
	fun error(msg: String)
	fun content(film: Film)
}