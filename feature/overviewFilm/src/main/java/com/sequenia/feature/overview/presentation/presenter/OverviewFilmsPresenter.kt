package com.sequenia.feature.overview.presentation.presenter

import com.sequenia.feature.overview.domain.usecase.GetFilmsUseCase
import com.sequenia.feature.overview.domain.usecase.GetGenresUseCase
import com.sequenia.feature.overview.domain.usecase.UpdateLocalDbUseCase
import com.sequenia.feature.overview.presentation.router.OverviewFilmsRouter
import com.sequenia.feature.overview.presentation.view.OverviewFilmsView
import moxy.MvpPresenter

class OverviewFilmsPresenter(
	private val router: OverviewFilmsRouter,
	private val getFilmsUseCase: GetFilmsUseCase,
	private val getGenresUseCase: GetGenresUseCase,
	private val updateLocalDbUseCase: UpdateLocalDbUseCase
) : MvpPresenter<OverviewFilmsView>()