package com.lukitor.projectandroidjetpackpro1.utils

import android.content.Context
import com.lukitor.projectandroidjetpackpro1.data.source.remote.response.MovieResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

class JsonHelper(private val context: Context) {
    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("Movie.json").toString())
            val listArray = responseObject.getJSONArray("Movies")
            for (i in 0 until listArray.length()) {
                val course = listArray.getJSONObject(i)
                val id = course.getString("id")
                val title = course.getString("judul")
                val imagePath = course.getString("image")
                val description = course.getString("description")
                val rating = course.getString("rating")
                val genre = course.getString("genre")
                val type = course.getString("type")
                val courseResponse =
                    MovieResponse(id, title, imagePath, description, rating, genre, type)
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}