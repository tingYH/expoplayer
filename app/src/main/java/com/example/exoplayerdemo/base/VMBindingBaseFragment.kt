package com.example.exoplayerdemo.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class VMBindingBaseFragment<VB : ViewDataBinding, VM : ViewModel> :
    BindingBaseFragment<VB>() {


    /**
     * 获取与DataBinding绑定的viewModel变量id
     */
    abstract fun getBindingViewModelId(): Int

    /**
     * 获取与DataBinding绑定的viewModel实例
     */
    abstract fun getBindingViewModel(): VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(getBindingViewModelId(), getBindingViewModel())
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onDestroyView() {
        binding.unbind()
        super.onDestroyView()
    }
}