package com.example.exoplayerdemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * [BaseFragment]子类提供仅使用viewBinding的情况，可选择是否需要继承
 */
abstract class BindingBaseFragment<VM : ViewBinding> : BaseFragment() {

    private var _binding: VM? = null
    protected val binding: VM
        get() = _binding
            ?: error("should be use `binding` after `onCreateView()` and before `onDestroyView()`")


    abstract fun createBinding(
        inflater: LayoutInflater,
        root: ViewGroup?,
        attachToRoot: Boolean
    ): VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = createBinding(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}