package com.lukitor.projectandroidjetpackpro1.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.lukitor.projectandroidjetpackpro1.`class`.DataDummy
import com.lukitor.projectandroidjetpackpro1.data.source.local.LocalDataSource
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.lukitor.projectandroidjetpackpro1.data.source.remote.RemoteDataSource
import com.lukitor.projectandroidjetpackpro1.utils.AppExecutors
import com.lukitor.projectandroidjetpackpro1.utils.PagedListUtil
import com.lukitor.projectandroidjetpackpro1.vo.Resource
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val testExecutors :AppExecutors = AppExecutors(TestExecutor(), TestExecutor(), TestExecutor())

    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val allDataResponses = DataDummy.generateRemoteAllData()
    private val movieResponses = DataDummy.generateRemoteMovieData()
    private val tvResponses = DataDummy.generateRemoteTVData()
    private val judulResponse = DataDummy.generateRemoteAllData()

    @Test
    fun getAllList() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntitiy>
        Mockito.`when`(local.getAllData()).thenReturn(dataSourceFactory)
        movieRepository.getAllData()
        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateAllData()))
        verify(local).getAllData()
        Assert.assertNotNull(movieEntities.data)
        assertEquals(allDataResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieList() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntitiy>
        Mockito.`when`(local.getAllMovie()).thenReturn(dataSourceFactory)
        movieRepository.getMovie()
        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovieData()))
        verify(local).getAllMovie()
        Assert.assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTVList() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntitiy>
        Mockito.`when`(local.getAllTV()).thenReturn(dataSourceFactory)
        movieRepository.getTV()
        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTVData()))
        verify(local).getAllTV()
        Assert.assertNotNull(movieEntities.data)
        assertEquals(tvResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getFavorite() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntitiy>
        Mockito.`when`(local.getAllFavorite()).thenReturn(dataSourceFactory)
        movieRepository.getFavorite("All")
        val courseEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateAllData()))
        verify(local).getAllFavorite()
        Assert.assertNotNull(courseEntities)
        assertEquals(allDataResponses.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getDetail() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntitiy>
        Mockito.`when`(local.getAllData()).thenReturn(dataSourceFactory)
        movieRepository.getAllData()
        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateAllData()))
        verify(local).getAllData()
        Assert.assertNotNull(movieEntities)
        assertEquals(judulResponse.get(0).judul, movieEntities.data?.get(0)?.judul)
        assertEquals(judulResponse.get(0).image, movieEntities.data?.get(0)?.image)
        assertEquals(judulResponse.get(0).description, movieEntities.data?.get(0)?.description)
        assertEquals(judulResponse.get(0).rating, movieEntities.data?.get(0)?.rating)
        assertEquals(judulResponse.get(0).genre, movieEntities.data?.get(0)?.genre)
        assertEquals(judulResponse.get(0).type, movieEntities.data?.get(0)?.type)
    }

    @Test
    fun setFavorite() {
        val dummyMovie: MovieEntitiy = DataDummy.generateAllData()[0]
        Mockito.`when`(appExecutors.diskIO()).thenReturn(testExecutors.diskIO())
        var newState = dummyMovie.favorite
        if (newState == 0) newState = 1
        else newState = 0
        Mockito.doNothing().`when`(local).setFavorite(dummyMovie,newState)
        movieRepository.setFavorite(dummyMovie,newState)
        verify(local, times(1)).setFavorite(dummyMovie,newState)
    }
}