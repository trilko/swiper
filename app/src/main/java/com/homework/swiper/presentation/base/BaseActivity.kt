package com.homework.swiper.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.homework.swiper.presentation.application.App

abstract class BaseActivity<VM: BaseViewModel>: AppCompatActivity() {

    private val viewModelFactory: ViewModelProvider.Factory = App.component.getViewModelFactory()

    val viewModel: VM by lazy { ViewModelProvider(this, viewModelFactory)[getViewModelClass()] }

    protected abstract fun getViewModelClass(): Class<VM>

}