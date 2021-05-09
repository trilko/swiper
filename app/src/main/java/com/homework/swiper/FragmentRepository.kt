package com.homework.swiper

import com.homework.swiper.data.models.FragmentModel

interface FragmentRepository {

    fun put(model: FragmentModel)

    fun get(): FragmentModel

}