package com.sequenia.sequenia.navigation.routers

import com.github.terrakok.cicerone.Router
import com.sequenia.feature.detailsfilm.presentation.router.FilmDetailsRouter

class FilmDetailRouterImpl(private val router: Router) : FilmDetailsRouter {

	override fun navigateBack() {
		router.exit()
	}
}