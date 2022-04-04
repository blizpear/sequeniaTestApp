package com.sequenia.feature.overview

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.sequenia.feature.overview.presentation.ui.OverviewFilmsFragment

fun getOverviewFilmScreen() = FragmentScreen { OverviewFilmsFragment.getInstance() }