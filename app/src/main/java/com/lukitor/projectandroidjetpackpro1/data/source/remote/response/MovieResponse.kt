package com.lukitor.projectandroidjetpackpro1.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    var id: String,
    var judul: String,
    var image: String,
    var description: String,
    var rating: String,
    var genre: String,
    var type: String
) : Parcelable