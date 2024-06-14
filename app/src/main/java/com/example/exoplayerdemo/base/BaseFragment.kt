package com.example.exoplayerdemo.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.example.exoplayerdemo.http.loadingDialog.LoadingDialog

open class BaseFragment : Fragment() {


    protected var mLoadingDialog: LoadingDialog? = null

    var isLoaded = false

    @CallSuper
    override fun onResume() {
        super.onResume()
        if (!isLoaded) {
            lazyLoad()
            isLoaded = true
        } else {
            afterLazyLoad()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mLoadingDialog = LoadingDialog(activity)
        // 防止事件穿透
        view.isClickable = true
        super.onViewCreated(view, savedInstanceState)
    }



    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    /**
     * 第一次可见，调用，一般用于懒加载
     */
    open fun lazyLoad() {

    }

    /**
     * 在第一次懒加载之后，如果切换resumue 可见需要刷新数据的情况
     */
    open fun afterLazyLoad() {

    }
}
