package com.sequenia.shared.filmsdatabase.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sequenia.shared.filmsdatabase.dao.FilmGenreCrossRefDao
import com.sequenia.shared.filmsdatabase.dao.FilmsDao
import com.sequenia.shared.filmsdatabase.dao.GenreDao
import com.sequenia.shared.filmsdatabase.dto.FilmDto
import com.sequenia.shared.filmsdatabase.dto.FilmGenreCrossRef
import com.sequenia.shared.filmsdatabase.dto.GenreDto

@Database(
	entities = [
		FilmDto::class,
		GenreDto::class,
		FilmGenreCrossRef::class
	],
	version = 1,
	exportSchema = false
)
abstract class FilmsDatabase : RoomDatabase() {

	abstract fun filmsDao(): FilmsDao
	abstract fun genreDao(): GenreDao
	abstract fun filmGenreCrossRefDao(): FilmGenreCrossRefDao
}