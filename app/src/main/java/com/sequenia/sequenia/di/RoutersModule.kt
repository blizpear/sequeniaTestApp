package com.sequenia.sequenia.di

import com.sequenia.feature.detailsfilm.presentation.router.FilmDetailsRouter
import com.sequenia.feature.overview.presentation.router.OverviewFilmsRouter
import com.sequenia.sequenia.navigation.routers.FilmDetailRouterImpl
import com.sequenia.sequenia.navigation.routers.OverviewFilmsRouterImpl
import org.koin.dsl.module

val routersModule = module {

	factory<OverviewFilmsRouter> { OverviewFilmsRouterImpl(router = get()) }
	factory<FilmDetailsRouter> { FilmDetailRouterImpl(router = get()) }
}