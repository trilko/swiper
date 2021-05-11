package com.homework.swiper.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi

object NotificationChannelSetting {

    const val id = "CHANNEL_01"

    @RequiresApi(Build.VERSION_CODES.O)
    fun createChannel(): NotificationChannel {
        val name = "TEST_CHANNEL"
        val importance = NotificationManager.IMPORTANCE_LOW
        val mChannel = NotificationChannel(id, name, importance)
        mChannel.enableLights(true)
        mChannel.lightColor = Color.RED
        mChannel.enableVibration(true)
        return mChannel
    }

}