package com.homework.swiper.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ActualFragment(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,

    val actualFragment: Int
)
