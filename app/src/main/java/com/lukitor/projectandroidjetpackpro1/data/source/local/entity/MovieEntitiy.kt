package com.lukitor.projectandroidjetpackpro1.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movieEntity")
@Parcelize
data class MovieEntitiy(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,
    @ColumnInfo(name = "judul")
    var judul: String,
    @ColumnInfo(name = "image")
    var image: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "rating")
    var rating: String,
    @ColumnInfo(name = "genre")
    var genre: String,
    @ColumnInfo(name = "type")
    var type: String,
    @ColumnInfo(name = "favorite")
    var favorite: Int = 0
) : Parcelable