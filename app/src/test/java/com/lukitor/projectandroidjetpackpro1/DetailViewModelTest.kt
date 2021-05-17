package com.lukitor.projectandroidjetpackpro1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lukitor.projectandroidjetpackpro1.`class`.DataDummy
import com.lukitor.projectandroidjetpackpro1.data.MovieRepository
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
@RunWith(MockitoJUnitRunner.Silent::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyData = DataDummy.generateAllData()
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var movieRepository: MovieRepository
    @Mock
    private lateinit var observer: Observer<List<MovieEntitiy>>
    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
    }
    @Test
    fun getData() {
        val data = MutableLiveData<List<MovieEntitiy>>()
        data.value = dummyData
        Mockito.`when`(movieRepository.getDetail(dummyData[0].judul)).thenReturn(data)
        val movieEntitiy = viewModel.getDetail(dummyData[0].judul).value
        Mockito.verify(movieRepository).getDetail(dummyData[0].judul)
        Assert.assertNotNull(movieEntitiy)
        assertEquals(dummyData[0].judul, movieEntitiy?.get(0)?.judul)
        assertEquals(dummyData[0].image, movieEntitiy?.get(0)?.image)
        assertEquals(dummyData[0].description, movieEntitiy?.get(0)?.description)
        assertEquals(dummyData[0].rating, movieEntitiy?.get(0)?.rating)
        assertEquals(dummyData[0].genre, movieEntitiy?.get(0)?.genre)
        assertEquals(dummyData[0].type, movieEntitiy?.get(0)?.type)
        assertEquals(dummyData[0].favorite, movieEntitiy?.get(0)?.favorite)
        viewModel.getDetail(dummyData[0].judul).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyData)
    }
    @Test
    fun testSetFavorite() {
        val movie = MutableLiveData<List<MovieEntitiy>>()
        movie.value = dummyData
        Mockito.`when`(movieRepository.getDetail(dummyData[0].id)).thenReturn(movie)
        val movieEntitiy = MovieEntitiy(
            dummyData?.get(0)?.id.toString(),
            dummyData?.get(0)?.judul.toString(),
            dummyData?.get(0)?.image.toString(),
            dummyData?.get(0)?.description.toString(),
            dummyData?.get(0)?.rating.toString(),
            dummyData?.get(0)?.genre.toString(),
            dummyData?.get(0)?.type.toString(),
            dummyData?.get(0)?.favorite!!.toInt()
        )
        viewModel.setFavorite(movieEntitiy)
        Mockito.verify(movieRepository).setFavorite(movieEntitiy, movieEntitiy.favorite)
        verifyNoMoreInteractions(observer)

    }
}