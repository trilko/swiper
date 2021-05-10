package com.homework.swiper.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FragmentEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,

    val amount: Int = 1,

    val actualFragment: Int = 1
)
