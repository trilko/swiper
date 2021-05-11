package com.homework.swiper.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class SwiperPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    private var listFragments: List<Fragment> = emptyList()

    fun putElements(newList: List<Fragment>) {
        listFragments = newList
        notifyDataSetChanged()
    }

    override fun getCount() = listFragments.size

    override fun getItem(position: Int) = listFragments[position]

}