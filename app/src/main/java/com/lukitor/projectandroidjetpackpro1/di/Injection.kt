package com.lukitor.projectandroidjetpackpro1.di

import android.content.Context
import com.lukitor.projectandroidjetpackpro1.data.MovieRepository
import com.lukitor.projectandroidjetpackpro1.data.source.local.LocalDataSource
import com.lukitor.projectandroidjetpackpro1.data.source.local.room.MovieDatabase
import com.lukitor.projectandroidjetpackpro1.data.source.remote.RemoteDataSource
import com.lukitor.projectandroidjetpackpro1.utils.AppExecutors
import com.lukitor.projectandroidjetpackpro1.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = MovieDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()
        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}