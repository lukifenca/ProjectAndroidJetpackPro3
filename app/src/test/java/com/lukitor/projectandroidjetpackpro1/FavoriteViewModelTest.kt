package com.lukitor.projectandroidjetpackpro1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.lukitor.projectandroidjetpackpro1.`class`.DataDummy
import com.lukitor.projectandroidjetpackpro1.data.MovieRepository
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
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
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var academyRepository: MovieRepository
    @Mock
    private lateinit var observer: Observer<List<MovieEntitiy>>
    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(academyRepository)
    }
    @Test
    fun getFavorite() {
        val expected = MutableLiveData<PagedList<MovieEntitiy>>()
        expected.value = PagedTestDataSources.snapshot(DataDummy.generateAllData())
        Mockito.`when`(academyRepository.getFavorite("All")).thenReturn(expected)
        viewModel.getBookmarks("All").observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModel.getBookmarks("All").value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        Assert.assertEquals(expectedValue?.size, actualValue?.size)
    }
    class PagedTestDataSources private constructor(private val items: List<MovieEntitiy>) :
        PositionalDataSource<MovieEntitiy>() {
        companion object {
            fun snapshot(items: List<MovieEntitiy> = listOf()): PagedList<MovieEntitiy> {
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