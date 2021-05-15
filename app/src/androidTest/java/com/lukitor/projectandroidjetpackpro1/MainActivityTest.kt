package com.lukitor.projectandroidjetpackpro1

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.lukitor.projectandroidjetpackpro1.`class`.DataDummy
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.lukitor.projectandroidjetpackpro1.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private lateinit var dataMovie: List<MovieEntitiy>
    private lateinit var dataTvShow: List<MovieEntitiy>
    private lateinit var dataAll: List<MovieEntitiy>

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        dataAll = DataDummy.generateAllData()
        dataMovie = DataDummy.generateMovieData()
        dataTvShow = DataDummy.generateTVData()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun testAll() {
        Espresso.onView(withId(R.id.menu_all)).perform(click())
        Espresso.onView(withId(R.id.rvFollowing))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvFollowing))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataAll.size))
    }

    @Test
    fun testDetailAll() {
        Espresso.onView(withId(R.id.menu_all)).perform(click())
        Espresso.onView(withId(R.id.rvFollowing)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                MyViewAction.clickOnViewChild(R.id.gambarcard)
            )
        )
        Espresso.onView(withId(R.id.txtDJudul))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtDJudul))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataAll[0].judul)))
        Espresso.onView(withId(R.id.txtdeskripsi))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtdeskripsi))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataAll[0].description)))
        Espresso.onView(withId(R.id.txtRating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtRating))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataAll[0].rating)))
        Espresso.onView(withId(R.id.txtGenre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtGenre))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataAll[0].genre)))
    }

    @Test
    fun testMovie() {
        Espresso.onView(withId(R.id.menu_movie)).perform(click())
        Espresso.onView(withId(R.id.rvFollowing))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvFollowing))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataMovie.size))
    }

    @Test
    fun testDetailMovie() {
        Espresso.onView(withId(R.id.menu_movie)).perform(click())
        Espresso.onView(withId(R.id.rvFollowing)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                5,
                MyViewAction.clickOnViewChild(R.id.gambarcard)
            )
        )
        Espresso.onView(withId(R.id.txtDJudul))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtDJudul))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataMovie[5].judul)))
        Espresso.onView(withId(R.id.txtdeskripsi))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtdeskripsi))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataMovie[5].description)))
        Espresso.onView(withId(R.id.txtRating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtRating))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataMovie[5].rating)))
        Espresso.onView(withId(R.id.txtGenre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtGenre))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataMovie[5].genre)))
    }

    @Test
    fun testTVShow() {
        Espresso.onView(withId(R.id.menu_tvshow)).perform(click())
        Espresso.onView(withId(R.id.rvFollowing))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvFollowing))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataTvShow.size))
    }

    @Test
    fun testDetailTVShow() {
        Espresso.onView(withId(R.id.menu_tvshow)).perform(click())
        Espresso.onView(withId(R.id.rvFollowing)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                MyViewAction.clickOnViewChild(R.id.gambarcard)
            )
        )
        Espresso.onView(withId(R.id.txtDJudul))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtDJudul))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataTvShow[0].judul)))
        Espresso.onView(withId(R.id.txtdeskripsi))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtdeskripsi))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataTvShow[0].description)))
        Espresso.onView(withId(R.id.txtRating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtRating))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataTvShow[0].rating)))
        Espresso.onView(withId(R.id.txtGenre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtGenre))
            .check(ViewAssertions.matches(ViewMatchers.withText(dataTvShow[0].genre)))
    }

    @Test
    fun loadFavorite() {
        Espresso.onView(withId(R.id.menu_all)).perform(click())
        Espresso.onView(withId(R.id.rvFollowing)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Espresso.onView(withId(R.id.ImgDetailFav)).perform(click())
        Espresso.onView(withId(R.id.imgback)).perform(click())
        Espresso.onView(withId(R.id.imgFav)).perform(click())
        Espresso.onView(withId(R.id.menu_all)).perform(click())
        Espresso.onView(withId(R.id.rvFollowingg))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvFollowingg)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Espresso.onView(withId(R.id.txtDJudul))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtdeskripsi))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtRating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtGenre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.ImgDetailFav)).perform(click())
        Espresso.onView(withId(R.id.imgback)).perform(click())
    }

}