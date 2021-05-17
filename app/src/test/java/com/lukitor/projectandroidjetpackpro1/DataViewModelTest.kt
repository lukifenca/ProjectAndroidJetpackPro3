package com.lukitor.projectandroidjetpackpro1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.lukitor.projectandroidjetpackpro1.`class`.DataDummy
import com.lukitor.projectandroidjetpackpro1.data.MovieRepository
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.lukitor.projectandroidjetpackpro1.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class DataViewModelTest {
    private lateinit var viewModel: DataViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var movieRepository: MovieRepository
    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntitiy>>>
    @Before
    fun setUp() {
        viewModel = DataViewModel(movieRepository)
    }
    @Test
    fun getAllList() {
        val courses = PagedTestDataSources.snapshotAll(DataDummy.generateAllData())
        val expected = MutableLiveData<Resource<PagedList<MovieEntitiy>>>()
        expected.value = Resource.success(courses)
        Mockito.`when`(movieRepository.getAllData()).thenReturn(expected)
        viewModel.getAllData().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModel.getAllData().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.data, actualValue?.data)
        Assert.assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }
    @Test
    fun getMovieList() {
        val courses = PagedTestDataSources.snapshot(DataDummy.generateMovieData())
        val expected = MutableLiveData<Resource<PagedList<MovieEntitiy>>>()
        expected.value = Resource.success(courses)
        Mockito.`when`(movieRepository.getMovie()).thenReturn(expected)
        viewModel.getMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModel.getMovie().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.data, actualValue?.data)
        Assert.assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }
    @Test
    fun getTvShowList() {
        val courses = PagedTestDataSources.snapshot(DataDummy.generateTVData())
        val expected = MutableLiveData<Resource<PagedList<MovieEntitiy>>>()
        expected.value = Resource.success(courses)
        Mockito.`when`(movieRepository.getTV()).thenReturn(expected)
        viewModel.getTV().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModel.getTV().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.data, actualValue?.data)
        Assert.assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }
    class PagedTestDataSources private constructor(private val items: List<MovieEntitiy>) :
        PositionalDataSource<MovieEntitiy>() {
        companion object {
            fun snapshot(items: List<MovieEntitiy> = listOf()): PagedList<MovieEntitiy> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
            fun snapshotAll(items: List<MovieEntitiy> = listOf()): PagedList<MovieEntitiy> {
                return PagedList.Builder(PagedTestDataSources(items), 20)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }
        override fun loadInitial(params: LoadInitialParams,callback: LoadInitialCallback<MovieEntitiy>) {
            callback.onResult(items, 0, items.size)
        }
        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieEntitiy>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}