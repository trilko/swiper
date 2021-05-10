package com.homework.swiper.domain

import androidx.lifecycle.LiveData
import com.homework.swiper.FragmentRepository
import com.homework.swiper.Interactor
import com.homework.swiper.data.models.FragmentModel
import javax.inject.Inject

class HandlerEvents @Inject constructor(
    private val repository: FragmentRepository,
    private val validator: ValidatorModel
): Interactor {

    override fun getModel(): LiveData<FragmentModel> = repository.model

    override suspend fun add(model: FragmentModel): Result {
        return if (validator.validateAdd(model)) {
            val result = model.copy(
                currentNumber = model.currentNumber + 1,
                amount = model.amount + 1
            )
            repository.add(result)
            Result.GOOD
        } else {
            Result.BAD
        }
    }

    override suspend fun remove(model: FragmentModel): Result {
        return if (validator.validateRemove(model)) {
            val result = model.copy(
                currentNumber = model.currentNumber - 1,
                amount = model.currentNumber - 1
            )
            repository.update(result)
            Result.GOOD
        } else {
            Result.BAD
        }
    }

    override suspend fun change(model: FragmentModel): Result {
        return if (validator.validateChange(model)) {
            val result = model.copy(
                currentNumber = model.currentNumber,
                amount = model.amount
            )
            repository.update(result)
            Result.GOOD
        } else {
            Result.BAD
        }
    }

}