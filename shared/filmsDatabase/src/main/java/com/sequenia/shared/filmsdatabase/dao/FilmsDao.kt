package com.sequenia.shared.filmsdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Transaction
import com.sequenia.shared.filmsdatabase.dto.FilmDto
import com.sequenia.shared.filmsdatabase.dto.FilmPreviewDto
import com.sequenia.shared.filmsdatabase.dto.FilmWithGenreDto

@Dao
interface FilmsDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(films: List<FilmDto>)

	@Transaction
	@Query("SELECT * FROM films_table WHERE filmId = :id")
	suspend fun getFilmWithGenre(id: Long): FilmWithGenreDto

	@Query("SELECT filmId, localizedName, imageUrl FROM films_table")
	suspend fun getPreviewFilms(): List<FilmPreviewDto>

	@Transaction
	@Query("SELECT * FROM films_table, genre_table WHERE genreId = :filter")
	@RewriteQueriesToDropUnusedColumns
	suspend fun getAllFilmsWithGenreFilter(filter: Long): List<FilmWithGenreDto>
}