package com.lukitor.projectandroidjetpackpro1.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.lukitor.projectandroidjetpackpro1.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao).apply { INSTANCE = this }
    }

    fun getAllData(): DataSource.Factory<Int, MovieEntitiy> = mMovieDao.getData()
    fun insertCourses(courses: List<MovieEntitiy>) = mMovieDao.insertData(courses)
    fun getAllMovie(): DataSource.Factory<Int, MovieEntitiy> = mMovieDao.getMovie()
    fun getDetail(id: String): LiveData<List<MovieEntitiy>> = mMovieDao.getDetail(id)
    fun getAllTV(): DataSource.Factory<Int, MovieEntitiy> = mMovieDao.getTV()
    fun getAllFavorite(): DataSource.Factory<Int, MovieEntitiy> = mMovieDao.getAllFavorite()
    fun getFavorite(type: String): DataSource.Factory<Int, MovieEntitiy> =
        mMovieDao.getFavorite(type)

    fun setFavorite(movie: MovieEntitiy, status: Int) {
        movie.favorite = status
        mMovieDao.updateFavorite(movie)
    }
}