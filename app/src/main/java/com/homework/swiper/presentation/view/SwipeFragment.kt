package com.homework.swiper.presentation.view

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.homework.swiper.R
import com.homework.swiper.databinding.FragmentSwipeBinding
import com.homework.swiper.presentation.Minus
import com.homework.swiper.presentation.Plus
import com.homework.swiper.presentation.base.BaseFragment
import com.homework.swiper.presentation.viewModel.SwiperViewModel
import com.homework.swiper.utils.NotificationChannelSetting


class SwipeFragment : BaseFragment<SwiperViewModel>() {

    override fun getViewModelClass() = SwiperViewModel::class.java

    private var _binding: FragmentSwipeBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val notification = "FROM_NOTIFICATION"
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
            if (Build.VERSION.SDK_INT >= 26) {
                createNotificationWithChannel()
            } else {
                createNotification()
            }
        }
    }

    private fun createNotification() {
        val notificationIntent = Intent(requireActivity(), MainActivity::class.java)
        notificationIntent.putExtra(notification, pageNumber)

        val notificationPendingIntent = PendingIntent.getActivity(
            requireContext(),
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(requireContext())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Chats heads active")
            .setContentText("Notification $pageNumber")
            .setContentIntent(notificationPendingIntent)
            .setAutoCancel(true)

        val notification = builder.build()
        val notificationManager = requireActivity().getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationWithChannel() {
        val notificationIntent = Intent(requireActivity(), MainActivity::class.java)
        notificationIntent.putExtra(notification, pageNumber)

        val notificationPendingIntent = PendingIntent.getActivity(
            requireContext(),
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(requireContext())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Chats heads active")
            .setContentText("Notification $pageNumber")
            .setChannelId(NotificationChannelSetting.id)
            .setContentIntent(notificationPendingIntent)
            .setAutoCancel(true)

        val notification = builder.build()
        val notificationManager = requireActivity().getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(NotificationChannelSetting.createChannel())
        notificationManager.notify(1, notification)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}