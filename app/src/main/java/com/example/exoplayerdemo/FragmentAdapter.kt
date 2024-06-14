package com.example.exoplayerdemo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragments: MutableList<Fragment> = mutableListOf()
    fun setList(list: List<Fragment>,) {
        fragments.clear()
        fragments.addAll(list)
        notifyItemRangeChanged(0, fragments.size)
    }

    fun geList() = fragments

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}