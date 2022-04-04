package com.sequenia.sequenia.di

import com.sequenia.feature.overview.presentation.router.OverviewFilmsRouter
import com.sequenia.sequenia.navigation.routers.OverviewFilmsRouterImpl
import org.koin.dsl.module

val routersModule = module {

	factory<OverviewFilmsRouter> { OverviewFilmsRouterImpl(router = get()) }
}