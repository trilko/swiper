package com.homework.swiper

import androidx.lifecycle.LiveData
import com.homework.swiper.data.models.FragmentModel

interface FragmentRepository {

    val model: LiveData<FragmentModel>

    suspend fun set(model: FragmentModel)

}