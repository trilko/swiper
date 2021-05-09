package com.homework.swiper.domain

import androidx.lifecycle.LiveData
import com.homework.swiper.FragmentRepository
import com.homework.swiper.Interactor
import com.homework.swiper.data.models.FragmentModel
import javax.inject.Inject

class HandlerEvents @Inject constructor(
    private val repository: FragmentRepository
): Interactor {

    override fun getAmountFragments(): LiveData<Int> = repository.amountFragments

    override fun getActualFragment(): LiveData<Int> = repository.actualFragment

    override suspend fun add(): Result {
        repository.add()
        return Result.GOOD
    }

    override suspend fun remove(model: FragmentModel): Result {
        return if (model.currentNumber >= 0) {
            repository.remove(model)
            Result.GOOD
        } else {
            Result.BAD
        }
    }

    override suspend fun change(model: FragmentModel): Result {
        return if (model.currentNumber >= 0) {
            repository.updateActual(model)
            Result.GOOD
        } else {
            Result.BAD
        }
    }

}