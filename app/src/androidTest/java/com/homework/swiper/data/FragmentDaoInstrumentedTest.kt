package com.homework.swiper.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.homework.swiper.data.entities.Fragments
import com.homework.swiper.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class FragmentDaoInstrumentedTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: AppDatabase
    private lateinit var fragmentDao: FragmentDao
    private lateinit var amountFragments: LiveData<Int>
    private lateinit var actualFragment: LiveData<Int>

    @Before
    fun createDao() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        fragmentDao = database.fragmentDao()
        amountFragments = fragmentDao.getAmount()
        actualFragment = fragmentDao.getActualFragment()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun whenInsertFragmentThenNewFragment() = runBlockingTest {
        val amountBefore = amountFragments.getOrAwaitValue()
        fragmentDao.add(Fragments(0))
        fragmentDao.add(Fragments(0))
        fragmentDao.add(Fragments(0))
        val amountAfter = amountFragments.getOrAwaitValue()
        assertEquals(amountBefore, amountAfter - 3)
    }
}