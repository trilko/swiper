package com.homework.swiper.data

import android.content.Context
import com.google.gson.Gson
import com.homework.swiper.FragmentRepository
import com.homework.swiper.data.models.FragmentMapper
import com.homework.swiper.data.models.FragmentModel
import javax.inject.Inject

class Preferences @Inject constructor(
    private val context: Context
): FragmentRepository {

    private val EXTRAS_STRING = "extras_string"
    private val FRAGMENT_DATA = "fragment_data"
    private val DEFAULT = Gson().toJson(FragmentModel(0, 0))

    override fun put(model: FragmentModel) {
        val pref = context.getSharedPreferences(EXTRAS_STRING, Context.MODE_PRIVATE)
        val ed = pref.edit()
        val entity = FragmentMapper().mapToEntity(model)
        ed.putString(FRAGMENT_DATA, entity)
        ed.apply()
    }

    override fun get(): FragmentModel {
        val pref = context.getSharedPreferences(EXTRAS_STRING, Context.MODE_PRIVATE)
        val gsonEntity = pref.getString(FRAGMENT_DATA, DEFAULT)
        return FragmentMapper().mapToModel(gsonEntity?: throw IllegalArgumentException())
    }

}