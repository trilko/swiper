package com.homework.swiper.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.homework.swiper.data.entities.ActualFragment
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
    fun insertExpectNewFragment() = runBlockingTest {
        val amountBefore = amountFragments.getOrAwaitValue()
        fragmentDao.add(Fragments(0))
        fragmentDao.add(Fragments(0))
        fragmentDao.add(Fragments(0))
        val amountAfter = amountFragments.getOrAwaitValue()
        assertEquals(0, amountBefore)
        assertEquals(3, amountAfter)
    }

    @Test
    fun removeSinceThreeExpectRemoveFive() = runBlockingTest {
        fragmentDao.add(Fragments(0))
        fragmentDao.add(Fragments(0))
        fragmentDao.add(Fragments(0))
        fragmentDao.add(Fragments(0))
        fragmentDao.add(Fragments(0))
        fragmentDao.add(Fragments(0))
        fragmentDao.add(Fragments(0))
        fragmentDao.add(Fragments(0))
        val amountBefore = amountFragments.getOrAwaitValue()
        fragmentDao.remove(3)
        val amountAfter = amountFragments.getOrAwaitValue()
        assertEquals(8, amountBefore)
        assertEquals(2, amountAfter)
    }

    @Test
    fun removeSinceThreeExpectNotRemove() = runBlockingTest {
        fragmentDao.add(Fragments(0))
        fragmentDao.add(Fragments(0))
        val amountBefore = amountFragments.getOrAwaitValue()
        fragmentDao.remove(3)
        val amountAfter = amountFragments.getOrAwaitValue()
        assertEquals(2, amountBefore)
        assertEquals(2, amountAfter)
    }

    @Test
    fun removeSinceZeroExpectNotRemove() = runBlockingTest {
        val amountBefore = amountFragments.getOrAwaitValue()
        fragmentDao.remove(0)
        val amountAfter = amountFragments.getOrAwaitValue()
        assertEquals(0, amountBefore)
        assertEquals(0, amountAfter)
    }

    @Test
    fun removeSinceNegativeNumberExpectNotRemove() = runBlockingTest {
        val amountBefore = amountFragments.getOrAwaitValue()
        fragmentDao.remove(-1)
        val amountAfter = amountFragments.getOrAwaitValue()
        assertEquals(0, amountBefore)
        assertEquals(0, amountAfter)
    }

    @Test
    fun updateSevenTimesExpectUpdateLiveDataNumberAllTimes() = runBlockingTest {
        fragmentDao.updateActualFragment(ActualFragment(id = 0, actualFragment = 6))
        val amountFirst = actualFragment.getOrAwaitValue()
        fragmentDao.updateActualFragment(ActualFragment(id = 0, actualFragment = 1))
        val amountSecond = actualFragment.getOrAwaitValue()
        fragmentDao.updateActualFragment(ActualFragment(id = 0, actualFragment = 5000))
        val amountThird = actualFragment.getOrAwaitValue()
        fragmentDao.updateActualFragment(ActualFragment(id = 0, actualFragment = 4))
        val amountFour = actualFragment.getOrAwaitValue()
        fragmentDao.updateActualFragment(ActualFragment(id = 0, actualFragment = 9))
        val amountFive = actualFragment.getOrAwaitValue()
        fragmentDao.updateActualFragment(ActualFragment(id = 0, actualFragment = 200))
        val amountSix = actualFragment.getOrAwaitValue()
        fragmentDao.updateActualFragment(ActualFragment(id = 0, actualFragment = 0))
        val amountSeven = actualFragment.getOrAwaitValue()
        assertEquals(6, amountFirst)
        assertEquals(1, amountSecond)
        assertEquals(5000, amountThird)
        assertEquals(4, amountFour)
        assertEquals(9, amountFive)
        assertEquals(200, amountSix)
        assertEquals(0, amountSeven)
    }

}