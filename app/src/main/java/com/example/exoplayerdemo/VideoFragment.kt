package com.example.exoplayerdemo

import android.net.Uri
import android.os.Bundle

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.exoplayerdemo.base.BindingBaseFragment
import com.example.exoplayerdemo.utils.TimeUtils
import com.example.exoplayerdemo.wedget.ExoPlayer
import com.example.exoplayerdemo.databinding.FragmentVideoBinding

class VideoFragment : BindingBaseFragment<FragmentVideoBinding>() {
    private var player: ExoPlayer? = null

    override fun createBinding(
        inflater: LayoutInflater,
        root: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentVideoBinding = FragmentVideoBinding.inflate(layoutInflater)

    override fun onResume() {
        super.onResume()
        val key = arguments?.getString(KEY_TYPE) ?: ""
        var user = arguments?.getString(KEY_USER) ?: ""
        val createTime = arguments?.getLong(KEY_CREATE_TIME) ?: 0
        var userName = arguments?.getString(KEY_USER_NAME) ?: ""
        user = user.replace("[","") //來不及處理array

        binding.tvName.text = userName.trim()
        binding.tvTime.text = "${TimeUtils.getFormatTime(createTime)}h"
        Glide.with(requireContext()).load("http://storage.googleapis.com/usr-framy/headshot/$user.jpg").into(binding.ivHead)
        player = ExoPlayer().apply {
            init(requireContext(), Uri.parse("https://storage.googleapis.com/pst-framy/vdo/${key}.mp4"), binding.playerView)
        }
    }

    override fun onPause() {
        super.onPause()
        player?.stop()
        player = null
    }

    companion object {
        private const val KEY_TYPE = "type"
        private const val KEY_USER = "user"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_CREATE_TIME = "create_time"
        fun newInstance(url: String, user:String,userName:String, createTime:Long): VideoFragment {
            val fragment = VideoFragment()
            val bundle = Bundle()
            bundle.putString(KEY_TYPE, url)
            bundle.putString(KEY_USER, user)
            bundle.putString(KEY_USER_NAME, userName)
            bundle.putLong(KEY_CREATE_TIME, createTime)
            fragment.arguments = bundle
            return fragment
        }
    }
}