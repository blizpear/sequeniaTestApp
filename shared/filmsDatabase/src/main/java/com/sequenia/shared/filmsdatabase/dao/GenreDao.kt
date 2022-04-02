package com.sequenia.shared.filmsdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sequenia.shared.filmsdatabase.dto.GenreDto

@Dao
interface GenreDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(genresList: List<GenreDto>)

	@Query("SELECT DISTINCT * FROM genre_table")
	suspend fun getUniqueGenre(): List<GenreDto>
}