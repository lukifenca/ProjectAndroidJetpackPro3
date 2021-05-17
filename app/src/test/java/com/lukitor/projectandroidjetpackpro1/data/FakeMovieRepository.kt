package com.lukitor.projectandroidjetpackpro1.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.lukitor.projectandroidjetpackpro1.data.source.local.LocalDataSource
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.lukitor.projectandroidjetpackpro1.data.source.remote.ApiResponse
import com.lukitor.projectandroidjetpackpro1.data.source.remote.RemoteDataSource
import com.lukitor.projectandroidjetpackpro1.data.source.remote.response.MovieResponse
import com.lukitor.projectandroidjetpackpro1.utils.AppExecutors
import com.lukitor.projectandroidjetpackpro1.vo.Resource
import java.util.*

class FakeMovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {
    override fun getAllData(): LiveData<Resource<PagedList<MovieEntitiy>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntitiy>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntitiy>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getAllData(), config).build()
            }
            override fun shouldFetch(data: PagedList<MovieEntitiy>?): Boolean =
                data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllData()
            public override fun saveCallResult(courseResponses: List<MovieResponse>) {
                val courseList = ArrayList<MovieEntitiy>()
                for (response in courseResponses) {
                    val course = MovieEntitiy(
                        response.id,
                        response.judul,
                        response.image,
                        response.description,
                        response.rating,
                        response.genre,
                        response.type
                    )
                    courseList.add(course)
                }
                localDataSource.insertCourses(courseList)
            }
        }.asLiveData()
    }
    override fun getMovie(): LiveData<Resource<PagedList<MovieEntitiy>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntitiy>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntitiy>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getAllMovie(), config).build()
            }
            override fun shouldFetch(data: PagedList<MovieEntitiy>?): Boolean =
                data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllData()
            public override fun saveCallResult(courseResponses: List<MovieResponse>) {
                val courseList = ArrayList<MovieEntitiy>()
                for (response in courseResponses) {
                    if (response.type == "Movie") {
                        val course = MovieEntitiy(
                            response.id,
                            response.judul,
                            response.image,
                            response.description,
                            response.rating,
                            response.genre,
                            response.type
                        )
                        courseList.add(course)
                    }
                }
                localDataSource.insertCourses(courseList)
            }
        }.asLiveData()
    }

    override fun getTV(): LiveData<Resource<PagedList<MovieEntitiy>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntitiy>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntitiy>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getAllTV(), config).build()
            }
            override fun shouldFetch(data: PagedList<MovieEntitiy>?): Boolean =
                data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllData()
            public override fun saveCallResult(courseResponses: List<MovieResponse>) {
                val courseList = ArrayList<MovieEntitiy>()
                for (response in courseResponses) {
                    if (response.type == "TV Show") {
                        val course = MovieEntitiy(
                            response.id,
                            response.judul,
                            response.image,
                            response.description,
                            response.rating,
                            response.genre,
                            response.type
                        )
                        courseList.add(course)
                    }
                }
                localDataSource.insertCourses(courseList)
            }
        }.asLiveData()
    }
    override fun getDetail(judul: String): LiveData<List<MovieEntitiy>> =
        localDataSource.getDetail(judul)
    override fun getFavorite(type: String): LiveData<PagedList<MovieEntitiy>> {
        val config =
            PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(4)
                .setPageSize(4).build()
        if (type == "" || type == "All") return LivePagedListBuilder(
            localDataSource.getAllFavorite(),
            config
        ).build()
        else return LivePagedListBuilder(localDataSource.getFavorite(type), config).build()
    }
    override fun setFavorite(movie: MovieEntitiy, status: Int) =
        appExecutors.diskIO().execute { localDataSource.setFavorite(movie, status) }
}