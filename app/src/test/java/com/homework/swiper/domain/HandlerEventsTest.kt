package com.homework.swiper.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.homework.swiper.FragmentRepository
import com.homework.swiper.data.models.FragmentModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HandlerEventsTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var mockHandler: HandlerEvents
    private lateinit var repository: FragmentRepository
    private val validator = ValidatorModel()

    @Before
    fun setUp() {
        repository = Mockito.mock(FragmentRepository::class.java)
        mockHandler = HandlerEvents(repository, validator)
    }

    @Test
    fun addZeroCurrentNumberExpectBadResult() = runBlockingTest {
        val model = FragmentModel(0, 5)
        val result = mockHandler.add(model)
        assertEquals(Result.BAD, result)
    }

    @Test
    fun addZeroAmountExpectBadResult() = runBlockingTest {
        val model = FragmentModel(0, 0)
        val result = mockHandler.add(model)
        assertEquals(Result.BAD, result)
    }

    @Test
    fun addCurrentNumberBiggerAmountExpectBadResult() = runBlockingTest {
        val model = FragmentModel(5, 2)
        val result = mockHandler.add(model)
        assertEquals(Result.BAD, result)
    }

    @Test
    fun addAmountBiggerCurrentNumberExpectGoodResult() = runBlockingTest {
        val model = FragmentModel(2, 5)
        val result = mockHandler.add(model)
        assertEquals(Result.GOOD, result)
    }

    @Test
    fun addCorrectDataExpectGoodResult() = runBlockingTest {
        val model = FragmentModel(5, 5)
        val result = mockHandler.add(model)
        assertEquals(Result.GOOD, result)
    }

    @Test
    fun removeOneCurrentNumberExpectBadResult() = runBlockingTest {
        val model = FragmentModel(1, 5)
        val result = mockHandler.remove(model)
        assertEquals(Result.BAD, result)
    }

    @Test
    fun removeOneAmountExpectBadResult() = runBlockingTest {
        val model = FragmentModel(1, 1)
        val result = mockHandler.remove(model)
        assertEquals(Result.BAD, result)
    }

    @Test
    fun removeCurrentNumberBiggerAmountExpectBadResult() = runBlockingTest {
        val model = FragmentModel(3, 2)
        val result = mockHandler.remove(model)
        assertEquals(Result.BAD, result)
    }

    @Test
    fun removeAmountBiggerCurrentNumberExpectGoodResult() = runBlockingTest {
        val model = FragmentModel(3, 5)
        val result = mockHandler.remove(model)
        assertEquals(Result.GOOD, result)
    }

    @Test
    fun removeCorrectDataExpectGoodResult() = runBlockingTest {
        val model = FragmentModel(5, 5)
        val result = mockHandler.remove(model)
        assertEquals(Result.GOOD, result)
    }

    @Test
    fun changeZeroCurrentNumberExpectBadResult() = runBlockingTest {
        val model = FragmentModel(0, 5)
        val result = mockHandler.change(model)
        assertEquals(Result.BAD, result)
    }

    @Test
    fun changeZeroAmountExpectBadResult() = runBlockingTest {
        val model = FragmentModel(0, 0)
        val result = mockHandler.change(model)
        assertEquals(Result.BAD, result)
    }

    @Test
    fun changeCurrentNumberBiggerAmountExpectBadResult() = runBlockingTest {
        val model = FragmentModel(4, 2)
        val result = mockHandler.change(model)
        assertEquals(Result.BAD, result)
    }

    @Test
    fun changeAmountBiggerCurrentNumberExpectGoodResult() = runBlockingTest {
        val model = FragmentModel(3, 5)
        val result = mockHandler.change(model)
        assertEquals(Result.GOOD, result)
    }

    @Test
    fun changeCorrectDataExpectGoodResult() = runBlockingTest {
        val model = FragmentModel(6, 6)
        val result = mockHandler.change(model)
        assertEquals(Result.GOOD, result)
    }
}