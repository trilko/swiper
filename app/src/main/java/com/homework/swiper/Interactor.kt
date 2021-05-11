package com.homework.swiper

import androidx.lifecycle.LiveData
import com.homework.swiper.data.models.FragmentModel
import com.homework.swiper.domain.Result

interface Interactor {

    fun getModel(): LiveData<FragmentModel>

    suspend fun add(model: FragmentModel): Result

    suspend fun remove(model: FragmentModel): Result

    suspend fun change(model: FragmentModel): Result

}