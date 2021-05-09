package com.homework.swiper

import androidx.lifecycle.LiveData
import com.homework.swiper.data.models.FragmentModel
import com.homework.swiper.domain.Result

interface Interactor {

    fun getAmountFragments(): LiveData<Int>

    fun getActualFragment(): LiveData<Int>

    suspend fun add(): Result

    suspend fun remove(model: FragmentModel): Result

    suspend fun change(model: FragmentModel): Result

}