package com.homework.swiper.presentation.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.homework.swiper.presentation.application.App

abstract class BaseFragment<VM: BaseViewModel>: Fragment() {

    private val viewModelFactory: ViewModelProvider.Factory = App.component.getViewModelFactory()

    val viewModel: VM by lazy { ViewModelProvider(requireActivity(), viewModelFactory)[getViewModelClass()] }

    protected abstract fun getViewModelClass(): Class<VM>

}