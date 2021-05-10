package com.homework.swiper

import androidx.lifecycle.LiveData
import com.homework.swiper.data.models.FragmentModel

interface FragmentRepository {

    val model: LiveData<FragmentModel>

    suspend fun add(model: FragmentModel)

    suspend fun update(model: FragmentModel)

}