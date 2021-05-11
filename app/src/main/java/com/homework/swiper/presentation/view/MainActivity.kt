package com.homework.swiper.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.homework.swiper.data.models.FragmentModel
import com.homework.swiper.databinding.ActivityMainBinding
import com.homework.swiper.presentation.Change
import com.homework.swiper.presentation.SwiperPagerAdapter
import com.homework.swiper.presentation.base.BaseActivity
import com.homework.swiper.presentation.viewModel.SwiperViewModel

class MainActivity: BaseActivity<SwiperViewModel>() {

    private lateinit var binding: ActivityMainBinding

    override fun getViewModelClass() = SwiperViewModel::class.java

    lateinit var pager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fromNotification = intent.getIntExtra(SwipeFragment.notification, -1)
        if (fromNotification != -1) {
            handleNotification(fromNotification)
        }
        initObservers()
        initViewPager()
    }

    private fun handleNotification(numberFragment: Int) {
        viewModel.handleEvent(Change(numberFragment - 1))
    }

    private fun initObservers() {
        viewModel.model.observe(this) {
            changeActualData(it)
        }
    }

    private fun changeActualData(model: FragmentModel) {
        val listFragments = ArrayList<Fragment>()
        repeat(model.amount) {
            listFragments.add(SwipeFragment.newInstance(it + 1))
        }
        val pagerAdapter = SwiperPagerAdapter(supportFragmentManager)
        pagerAdapter.putElements(listFragments)
        pager.adapter = pagerAdapter
        pager.currentItem = model.currentNumber
    }

    private fun initViewPager() {
        pager = binding.vPager
    }

}