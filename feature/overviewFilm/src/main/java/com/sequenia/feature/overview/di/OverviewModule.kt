package com.sequenia.feature.overview.di

import com.sequenia.feature.overview.data.api.FilmsApi
import com.sequenia.feature.overview.data.datasource.local.FilmGenreCrossRefDataSource
import com.sequenia.feature.overview.data.datasource.local.FilmGenreCrossRefDataSourceImpl
import com.sequenia.feature.overview.data.datasource.local.FilmsDbDataSource
import com.sequenia.feature.overview.data.datasource.local.FilmsDbDataSourceImpl
import com.sequenia.feature.overview.data.datasource.local.GenreDbDataSource
import com.sequenia.feature.overview.data.datasource.local.GenreDbDataSourceImpl
import com.sequenia.feature.overview.data.datasource.remote.FilmsRemoteDataSource
import com.sequenia.feature.overview.data.datasource.remote.FilmsRemoteDataSourceImpl
import com.sequenia.feature.overview.data.repository.OverviewFilmRepositoryImpl
import com.sequenia.feature.overview.domain.repository.OverviewFilmRepository
import com.sequenia.feature.overview.domain.usecase.GetFilmsUseCase
import com.sequenia.feature.overview.domain.usecase.GetGenresUseCase
import com.sequenia.feature.overview.domain.usecase.UpdateLocalDbUseCase
import com.sequenia.feature.overview.presentation.presenter.OverviewFilmsPresenter
import org.koin.dsl.module
import retrofit2.Retrofit

val overviewModule = module {

	factory<FilmsApi> { get<Retrofit>().create(FilmsApi::class.java) }

	factory<FilmGenreCrossRefDataSource> { FilmGenreCrossRefDataSourceImpl(dao = get()) }
	factory<FilmsDbDataSource> { FilmsDbDataSourceImpl(dao = get()) }
	factory<GenreDbDataSource> { GenreDbDataSourceImpl(dao = get()) }
	factory<FilmsRemoteDataSource> { FilmsRemoteDataSourceImpl(api = get()) }

	factory<OverviewFilmRepository> {
		OverviewFilmRepositoryImpl(
			filmsRemoteDataSource = get(),
			filmsDbDataSource = get(),
			genreDbDataSource = get(),
			filmGenreCrossRefDataSource = get()
		)
	}

	factory { UpdateLocalDbUseCase(repository = get()) }
	factory { GetFilmsUseCase(repository = get()) }
	factory { GetGenresUseCase(repository = get()) }

	factory {
		OverviewFilmsPresenter(
			router = get(),
			getFilmsUseCase = get(),
			getGenresUseCase = get(),
			updateLocalDbUseCase = get()
		)
	}
}