package com.homework.swiper.data.models

import com.google.gson.Gson
import com.homework.swiper.utils.Mapper

class FragmentMapper: Mapper<String, FragmentModel> {

    override fun mapToEntity(model: FragmentModel): String = with(model) {
        Gson().toJson(this)
    }

    override fun mapToModel(entity: String): FragmentModel = with(entity) {
        Gson().fromJson(this, FragmentModel::class.java)
    }

}