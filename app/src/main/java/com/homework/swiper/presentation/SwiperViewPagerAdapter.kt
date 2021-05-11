package com.homework.swiper.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.homework.swiper.R

class SwiperViewPagerAdapter: RecyclerView.Adapter<SwiperViewPagerAdapter.ViewPagerViewHolder>() {
    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(number: Int) = with(itemView) {
            val editText = this.findViewById(R.id.bottomButtonSpace) as EditText
            editText.setText(number)
        }
    }

    private var amountFragments: Int = 0

    fun putFragments(input: Int) {
        amountFragments = input
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewPagerViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fragment_swipe, parent, false)
    )

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = amountFragments
}