package com.homework.swiper

import androidx.lifecycle.LiveData
import com.homework.swiper.data.entities.ActualFragment
import com.homework.swiper.data.entities.Fragments
import com.homework.swiper.data.models.FragmentModel

interface FragmentRepository {

    val amountFragments: LiveData<Int>
    val actualFragment: LiveData<Int>

    suspend fun add()

    suspend fun remove(model: FragmentModel)

    suspend fun updateActual(model: FragmentModel)

}