package com.example.moviedbmvvm.data.db.entity

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllMovie(movies: List<Movie>)

    @Query(" SELECT * FROM Movie ")
    fun getAllMovie(): DataSource.Factory<Int,Movie>
    //fun getAllMovie(): LiveData<List<Movie>>

    @Query(" DELETE FROM Movie")
    fun deleteAll()
}