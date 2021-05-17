package com.lukitor.projectandroidjetpackpro1

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lukitor.projectandroidjetpackpro1.data.MovieRepository
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getDetail(judul: String): LiveData<List<MovieEntitiy>> = movieRepository.getDetail(judul)
    fun setFavorite(moive: MovieEntitiy) {
        val newState = moive.favorite
        movieRepository.setFavorite(moive, newState)
    }
}