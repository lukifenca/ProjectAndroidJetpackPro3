package com.lukitor.projectandroidjetpackpro1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lukitor.projectandroidjetpackpro1.`class`.DataDummy
import com.lukitor.projectandroidjetpackpro1.data.MovieRepository
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
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
        val data = MutableLiveData<List<MovieEntitiy>>()
        data.value = dummyData
        Mockito.`when`(movieRepository.getDetail(dummyData[0].judul)).thenReturn(data)
        var dataentity = viewModel.getDetail(dummyData[0].judul).value
        var movieEntitiy = MovieEntitiy(
            dataentity?.get(0)?.id.toString(),
            dataentity?.get(0)?.judul.toString(),
            dataentity?.get(0)?.image.toString(),
            dataentity?.get(0)?.description.toString(),
            dataentity?.get(0)?.rating.toString(),
            dataentity?.get(0)?.genre.toString(),
            dataentity?.get(0)?.type.toString(),
            dataentity?.get(0)?.favorite!!.toInt()
        )
        Mockito.verify(movieRepository).getDetail(dummyData[0].judul)
        Assert.assertNotNull(movieEntitiy)
        viewModel.setFavorite(movieEntitiy)
        viewModel.getDetail((dummyData[0].judul)).observeForever(observer)
        Mockito.verify(observer).onChanged(data.value)
        assertEquals(data.value, dataentity)
    }
}