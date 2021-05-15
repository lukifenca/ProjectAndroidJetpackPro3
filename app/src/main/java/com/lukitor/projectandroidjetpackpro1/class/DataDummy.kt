package com.lukitor.projectandroidjetpackpro1.`class`

import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.lukitor.projectandroidjetpackpro1.data.source.remote.response.MovieResponse

object DataDummy {
    fun generateAllData(): List<MovieEntitiy> {
        val id = Data.id
        val judul = Data.judul
        val description = Data.description
        val image = Data.image
        val rating = Data.rating
        val genre = Data.genre
        val type = Data.type
        val listData = ArrayList<MovieEntitiy>()
        judul.forEachIndexed { index, s ->
            var datamodel = MovieEntitiy(
                id[index],
                s,
                image[index],
                description[index],
                rating[index],
                genre[index],
                type[index]
            )
            listData.add(datamodel)
        }
        return listData
    }

    fun generateRemoteAllData(): List<MovieResponse> {
        val id = Data.id
        val judul = Data.judul
        val description = Data.description
        val image = Data.image
        val rating = Data.rating
        val genre = Data.genre
        val type = Data.type
        val listData = ArrayList<MovieResponse>()
        judul.forEachIndexed { index, s ->
            var datamodel = MovieResponse(
                id[index],
                s,
                image[index],
                description[index],
                rating[index],
                genre[index],
                type[index]
            )
            listData.add(datamodel)
        }
        return listData
    }

    fun generateMovieData(): List<MovieEntitiy> {
        val id = Data.id
        val judul = Data.judul
        val description = Data.description
        val image = Data.image
        val rating = Data.rating
        val genre = Data.genre
        val type = Data.type
        val listData = ArrayList<MovieEntitiy>()
        judul.forEachIndexed { index, s ->
            if (type[index] == "Movie") {
                var datamodel = MovieEntitiy(
                    id[index],
                    s,
                    image[index],
                    description[index],
                    rating[index],
                    genre[index],
                    type[index]
                )
                listData.add(datamodel)
            }
        }
        return listData
    }

    fun generateRemoteMovieData(): List<MovieResponse> {
        val id = Data.id
        val judul = Data.judul
        val description = Data.description
        val image = Data.image
        val rating = Data.rating
        val genre = Data.genre
        val type = Data.type
        val listData = ArrayList<MovieResponse>()
        judul.forEachIndexed { index, s ->
            if (type[index] == "Movie") {
                var datamodel = MovieResponse(
                    id[index],
                    s,
                    image[index],
                    description[index],
                    rating[index],
                    genre[index],
                    type[index]
                )
                listData.add(datamodel)
            }
        }
        return listData
    }

    fun generateTVData(): List<MovieEntitiy> {
        val id = Data.id
        val judul = Data.judul
        val description = Data.description
        val image = Data.image
        val rating = Data.rating
        val genre = Data.genre
        val type = Data.type
        val listData = ArrayList<MovieEntitiy>()
        judul.forEachIndexed { index, s ->
            if (type[index] == "TV Show") {
                var datamodel = MovieEntitiy(
                    id[index],
                    s,
                    image[index],
                    description[index],
                    rating[index],
                    genre[index],
                    type[index]
                )
                listData.add(datamodel)
            }
        }
        return listData
    }

    fun generateRemoteTVData(): List<MovieResponse> {
        val id = Data.id
        val judul = Data.judul
        val description = Data.description
        val image = Data.image
        val rating = Data.rating
        val genre = Data.genre
        val type = Data.type
        val listData = ArrayList<MovieResponse>()
        judul.forEachIndexed { index, s ->
            if (type[index] == "TV Show") {
                var datamodel = MovieResponse(
                    id[index],
                    s,
                    image[index],
                    description[index],
                    rating[index],
                    genre[index],
                    type[index]
                )
                listData.add(datamodel)
            }
        }
        return listData
    }
    fun generateBookmarked(movie : MovieEntitiy,bookmarked:Int):MovieEntitiy{
        movie.favorite=bookmarked
        return movie
    }
}