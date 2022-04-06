package com.sequenia.feature.detailsfilm

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.sequenia.feature.detailsfilm.presentation.ui.FilmDetailsFragment

fun getDetailsScreen(filmId: Long) = FragmentScreen { FilmDetailsFragment.getInstance(filmId) }