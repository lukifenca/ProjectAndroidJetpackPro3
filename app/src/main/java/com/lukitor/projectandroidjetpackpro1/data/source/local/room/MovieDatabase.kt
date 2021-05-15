package com.lukitor.projectandroidjetpackpro1.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy

@Database(entities = [MovieEntitiy::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun academyDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null
        fun getInstance(context: Context): MovieDatabase = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "Movies.db"
            ).build().apply { INSTANCE = this }
        }
    }
}