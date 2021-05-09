package com.homework.swiper.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fragments(
    @PrimaryKey(autoGenerate = true)
    val id: Int
)
