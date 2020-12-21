package com.example.moviedbmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviedbmvvm.data.db.entity.Movie
import com.example.moviedbmvvm.data.db.entity.MovieDao

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMovieDao() : MovieDao

    companion object{
        @Volatile
        private var instance: AppDatabase ?= null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MovieDb.db"
            ).build()

        }
       /* suspend fun populate(movieDao: MovieDao){
            withContext(Dispatchers.IO) {
                movieDao.deleteAll()
            }
        }*/
   // }
}