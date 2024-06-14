package com.example.exoplayerdemo.wedget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.exoplayerdemo.R

class BannerIndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    init {
        initXmlAttrs(context, attrs)
    }

    private var selectDrawable = 0
    private var normalDrawable = 0

    private var indicatorCount = 0

    private var indicatorSelectWidth = 0f
    private var indicatorSelectHeight = 0f
    private var indicatorNormalWidth = 0f
    private var indicatorNormalHeight = 0f

    private var indicatorMargin = 10f

    private fun initXmlAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BannerIndicatorView)
        indicatorSelectWidth = typedArray.getDimension(R.styleable.BannerIndicatorView_indicator_select_width, 0f)
        indicatorSelectHeight = typedArray.getDimension(R.styleable.BannerIndicatorView_indicator_select_height, 0f)
        indicatorNormalWidth = typedArray.getDimension(R.styleable.BannerIndicatorView_indicator_normal_width, 0f)
        indicatorNormalHeight = typedArray.getDimension(R.styleable.BannerIndicatorView_indicator_normal_height, 0f)
        indicatorMargin = typedArray.getDimension(R.styleable.BannerIndicatorView_indicator_margins, 0f)
        selectDrawable = typedArray.getResourceId(R.styleable.BannerIndicatorView_indicator_select_drawable, 0)
        normalDrawable = typedArray.getResourceId(R.styleable.BannerIndicatorView_indicator_normal_drawable, 0)
        typedArray.recycle()
    }

    private fun initIndicatorView(context: Context) {
        removeAllViews()
        for (i in 0 until indicatorCount) {
            val ivIndicator = AppCompatImageView(context)

            val widthParam = if (i == 0) indicatorSelectWidth.toInt() else indicatorNormalWidth.toInt()
            val heightParam = if (i == 0) indicatorSelectHeight.toInt() else indicatorNormalHeight.toInt()

            val lp = LayoutParams(widthParam, heightParam)
            lp.leftMargin = if (i == 0) 0 else indicatorMargin.toInt()
            ivIndicator.layoutParams = lp
            ivIndicator.setBackgroundResource(if (i == 0) selectDrawable else normalDrawable)
            addView(ivIndicator)
        }
    }

    fun initIndicatorCount(count: Int) {
        this.indicatorCount = count
        initIndicatorView(context)
    }

    fun changeIndicator(position: Int) {
        val count = childCount
        for (i in 0 until count) {
            val ivIndicator = getChildAt(i) as AppCompatImageView
            val widthParam =
                if (i == position) indicatorSelectWidth.toInt() else indicatorNormalWidth.toInt()
            val heightParam =
                if (i == position) indicatorSelectHeight.toInt() else indicatorNormalHeight.toInt()

            val lp = LayoutParams(widthParam, heightParam)
            lp.leftMargin = if (i == 0) 0 else indicatorMargin.toInt()
            ivIndicator.layoutParams = lp
            ivIndicator.setBackgroundResource(if (i == position) selectDrawable else normalDrawable)
        }
    }

}