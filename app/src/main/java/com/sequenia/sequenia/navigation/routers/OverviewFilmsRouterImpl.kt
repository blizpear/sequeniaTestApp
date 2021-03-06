package com.sequenia.sequenia.navigation.routers

import com.github.terrakok.cicerone.Router
import com.sequenia.feature.detailsfilm.getDetailsScreen
import com.sequenia.feature.overview.presentation.router.OverviewFilmsRouter

class OverviewFilmsRouterImpl(private val router: Router) : OverviewFilmsRouter {

	override fun navigateToDetailsFilmScreen(filmId: Long) {
		router.navigateTo(getDetailsScreen(filmId))
	}
}