package com.sequenia.shared.filmsdatabase.di

import androidx.room.Room
import com.sequenia.shared.filmsdatabase.database.FilmsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val filmDatabaseModule = module {

	factory { get<FilmsDatabase>().filmsDao() }
	factory { get<FilmsDatabase>().genreDao() }
	factory { get<FilmsDatabase>().filmGenreCrossRefDao() }

	single {
		Room.databaseBuilder(
			androidContext(),
			FilmsDatabase::class.java,
			FilmsDatabase.DATABASE_NAME
		).build()
	}
}