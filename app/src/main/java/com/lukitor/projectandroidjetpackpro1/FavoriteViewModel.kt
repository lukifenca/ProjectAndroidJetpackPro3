package com.lukitor.projectandroidjetpackpro1

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lukitor.projectandroidjetpackpro1.data.MovieRepository
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy

class FavoriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getBookmarks(type: String): LiveData<PagedList<MovieEntitiy>> {
        return movieRepository.getFavorite(type)
    }
}