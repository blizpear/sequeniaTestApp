package com.sequenia.feature.detailsfilm.di

import com.sequenia.feature.detailsfilm.data.datasource.FilmDetailsDataSource
import com.sequenia.feature.detailsfilm.data.datasource.FilmDetailsDataSourceImpl
import com.sequenia.feature.detailsfilm.data.repository.FilmDetailsRepositoryImpl
import com.sequenia.feature.detailsfilm.domain.repository.FilmDetailsRepository
import com.sequenia.feature.detailsfilm.domain.usecase.GetFilmUseCase
import com.sequenia.feature.detailsfilm.presentation.presenter.FilmDetailsPresenter
import org.koin.dsl.module

val filmDetailsModule = module {

	factory<FilmDetailsDataSource> { FilmDetailsDataSourceImpl(dao = get()) }

	factory<FilmDetailsRepository> {
		FilmDetailsRepositoryImpl(
			filmDetailsDataSource = get()
		)
	}

	factory { GetFilmUseCase(repository = get()) }

	factory {
		FilmDetailsPresenter(
			router = get(),
			getFilmUseCase = get(),
		)
	}
}