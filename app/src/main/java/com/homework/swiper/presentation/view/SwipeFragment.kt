package com.homework.swiper.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homework.swiper.databinding.FragmentSwipeBinding
import com.homework.swiper.presentation.CreateNotification
import com.homework.swiper.presentation.Minus
import com.homework.swiper.presentation.Plus
import com.homework.swiper.presentation.SwiperEvents
import com.homework.swiper.presentation.base.BaseFragment
import com.homework.swiper.presentation.viewModel.SwiperViewModel

class SwipeFragment : BaseFragment<SwiperViewModel>() {

    override fun getViewModelClass() = SwiperViewModel::class.java

    private var _binding: FragmentSwipeBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val args: String = "ARG_PAGE_NUMBER"

        fun newInstance(page: Int) = SwipeFragment().apply {
            arguments = Bundle().apply {
                putInt(args, page)
            }
        }
    }

    private val pageNumber: Int by lazy {
        arguments?.getInt(args)
            ?: 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSwipeBinding.inflate(inflater, container, false)
        binding.bottomButtonSpace.setText(pageNumber.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (pageNumber < 2) {
            binding.minusButton.visibility = View.GONE
        }
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.minusButton.setOnClickListener {
            viewModel.handleEvent(Minus(pageNumber))
        }
        binding.plusButton.setOnClickListener {
            viewModel.handleEvent(Plus(pageNumber))
        }
        binding.newNotification.setOnClickListener {
            viewModel.handleEvent(CreateNotification(pageNumber))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}