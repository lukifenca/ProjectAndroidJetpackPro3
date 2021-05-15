package com.lukitor.projectandroidjetpackpro1.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lukitor.projectandroidjetpackpro1.data.source.remote.response.MovieResponse
import com.lukitor.projectandroidjetpackpro1.utils.EspressoIdlingResource
import com.lukitor.projectandroidjetpackpro1.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) { RemoteDataSource(helper).apply { instance = this } }
    }

    fun getAllData(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultCourse = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        handler.postDelayed({
            resultCourse.value = ApiResponse.success(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultCourse
    }
}
