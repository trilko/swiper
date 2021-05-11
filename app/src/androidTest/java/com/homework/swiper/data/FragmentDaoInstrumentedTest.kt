package com.homework.swiper.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.homework.swiper.data.entities.FragmentEntity
import com.homework.swiper.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class FragmentDaoInstrumentedTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: AppDatabase
    private lateinit var fragmentDao: FragmentDao
    private lateinit var model: LiveData<FragmentEntity>

    @Before
    fun createDao() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        fragmentDao = database.fragmentDao()
        model = fragmentDao.getFragmentEntity()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun addExpectOneElement() = runBlockingTest {
        val expected = FragmentEntity(0, 1, 1)
        fragmentDao.add(expected)
        val real = model.getOrAwaitValue()
        assertEquals(expected, real)
    }

    @Test
    fun addFiveExpectOneElement() = runBlockingTest {
        val one = FragmentEntity(0, 1, 1)
        val two = FragmentEntity(0, 2, 2)
        val three = FragmentEntity(0, 3, 3)
        val four = FragmentEntity(0, 4, 4)
        val five = FragmentEntity(0, 99, 99)
        fragmentDao.add(one)
        fragmentDao.add(two)
        fragmentDao.add(three)
        fragmentDao.add(four)
        fragmentDao.add(five)
        val real = model.getOrAwaitValue()
        assertEquals(five, real)
        assertNotEquals(four, real)
        assertNotEquals(three, real)
        assertNotEquals(two, real)
        assertNotEquals(one, real)
    }

    @Test
    fun insertExpectNewFragment() = runBlockingTest {
        fragmentDao.add(FragmentEntity(0, 1, 1))
        fragmentDao.add(FragmentEntity(0, 2, 2))
        fragmentDao.add(FragmentEntity(0, 3, 3))
        fragmentDao.add(FragmentEntity(0, 4, 4))
        fragmentDao.add(FragmentEntity(0, 99, 99))

        val expected = FragmentEntity(0, 5, 8)
        fragmentDao.update(expected)
        val real = model.getOrAwaitValue()
        assertEquals(expected, real)
    }

}