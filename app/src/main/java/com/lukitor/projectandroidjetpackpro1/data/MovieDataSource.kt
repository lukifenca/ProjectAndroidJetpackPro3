package com.lukitor.projectandroidjetpackpro1.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.lukitor.projectandroidjetpackpro1.vo.Resource

interface MovieDataSource {
    fun getAllData(): LiveData<Resource<PagedList<MovieEntitiy>>>
    fun getMovie(): LiveData<Resource<PagedList<MovieEntitiy>>>
    fun getTV(): LiveData<Resource<PagedList<MovieEntitiy>>>
    fun getDetail(judul: String): LiveData<List<MovieEntitiy>>
    fun getFavorite(type: String): LiveData<PagedList<MovieEntitiy>>
    fun setFavorite(movie: MovieEntitiy, status: Int)
}