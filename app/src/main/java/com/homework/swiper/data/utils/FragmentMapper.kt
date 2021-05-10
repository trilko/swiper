package com.homework.swiper.data.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.homework.swiper.data.entities.FragmentEntity
import com.homework.swiper.data.models.FragmentModel

class FragmentMapper: Mapper<FragmentEntity, FragmentModel> {

    override fun mapToEntity(model: FragmentModel): FragmentEntity = with(model) {
        return FragmentEntity(0, this.amount, this.currentNumber)
    }

    override fun mapToModel(entity: FragmentEntity): FragmentModel = with(entity) {
        return FragmentModel(this.actualFragment, this.amount)
    }

    fun mapToModel(entityLiveData: LiveData<FragmentEntity>) = Transformations.map(entityLiveData) { entity -> mapToModel(entity) }

    fun mapToEntity(modelLiveData: LiveData<FragmentModel>) = Transformations.map(modelLiveData) { model -> mapToEntity(model) }
}