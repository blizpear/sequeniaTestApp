package com.sequenia.sequenia.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.sequenia.sequenia.navigation.provideCicerone
import org.koin.dsl.module

val navigationModule = module {

	single { provideCicerone() }
	single { get<Cicerone<Router>>().router }
	single { get<Cicerone<Router>>().getNavigatorHolder() }
}