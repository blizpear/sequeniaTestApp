package com.sequenia.shared.filmsdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.sequenia.shared.filmsdatabase.dto.FilmGenreCrossRef

@Dao
interface FilmGenreCrossRefDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(ref: List<FilmGenreCrossRef>)
}