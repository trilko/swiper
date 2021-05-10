package com.homework.swiper.domain

import com.homework.swiper.data.models.FragmentModel
import javax.inject.Inject

class ValidatorModel @Inject constructor() {

    fun validateAdd(model: FragmentModel): Boolean {
        val current = model.currentNumber
        val amount = model.amount

        if (current < 1) {
            return false
        }
        if (amount < 1) {
            return false
        }
        if (current > amount) {
            return false
        }

        return true
    }

    fun validateRemove(model: FragmentModel): Boolean {
        val current = model.currentNumber
        val amount = model.amount

        if (current < 2) {
            return false
        }
        if (amount < 2) {
            return false
        }
        if (current > amount) {
            return false
        }

        return true
    }

    fun validateChange(model: FragmentModel): Boolean {
        val current = model.currentNumber
        val amount = model.amount

        if (current < 1) {
            return false
        }
        if (amount < 1) {
            return false
        }
        if (current > amount) {
            return false
        }

        return true
    }

}