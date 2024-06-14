package com.example.exoplayerdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.exoplayerdemo.databinding.ActivityMainBinding
import com.example.exoplayerdemo.http.GoogleApiWrapper
import com.example.exoplayerdemo.http.rxandroid.WebFailAction
import rx.subscriptions.CompositeSubscription

class MainActivity : AppCompatActivity() {
    private var index: Int = 0
    private val mCompositeSubscription = CompositeSubscription()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val tabFragments = mutableListOf<Fragment>()

    private val adapter by lazy {
        FragmentAdapter(
            supportFragmentManager,
            lifecycle
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.apply {
            vp.adapter = adapter
        }
        initData()

    }

    private val mOnPageChangeCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.bivNews.changeIndicator(position)
        }
    }


   private fun initData (){
        mCompositeSubscription.add(
            GoogleApiWrapper.getInstance()
                .sendMSG( )
                .subscribe({ res ->
                    res.p.forEachIndexed { page, it->
                        dataSetup(page,it.att.id,it.source[2].toString().split(",")[0],it.source[2].toString().split(",")[2],it.at)
                    }
                    with(binding) {
                        if (vp.adapter != null) {
                            index = vp.currentItem
                            vp.adapter = null
                        }
                        with(vp) {
                            unregisterOnPageChangeCallback(mOnPageChangeCallback)
                            registerOnPageChangeCallback(mOnPageChangeCallback)
                            offscreenPageLimit = tabFragments.size - 1
                            adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {
                                override fun getItemCount(): Int = tabFragments.size
                                override fun createFragment(position: Int): Fragment {
                                    return tabFragments[position]
                                }
                            }

                            binding.bivNews.initIndicatorCount(tabFragments.size)
                            currentItem = index
                        }
                    }
                }, WebFailAction())
        )
    }

    private fun dataSetup(page: Int, url :String, userId:String, username:String, createTime:Long){
        tabFragments.add(page, VideoFragment.newInstance(url,userId,username,createTime))
    }
}