package com.homework.swiper

import androidx.lifecycle.LiveData
import com.homework.swiper.data.entities.ActualFragment
import com.homework.swiper.data.entities.Fragments

interface FragmentRepository {

    suspend fun add(fragment: Fragments)

    suspend fun remove(fragment: Fragments): Int

    fun getAmount(): LiveData<Int>

    fun getActualFragment(): LiveData<Int>

    suspend fun updateActualFragment(actual: ActualFragment): Int

}