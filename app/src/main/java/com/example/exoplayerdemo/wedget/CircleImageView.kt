package com.example.exoplayerdemo.wedget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {


    private val borderPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, android.R.color.white)
        style = Paint.Style.STROKE
        strokeWidth = 2 * context.resources.displayMetrics.density
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        val radius = Math.min(width, height) / 2f
        val centerX = width / 2f
        val centerY = height / 2f

        // Draw the white border
        canvas.drawCircle(centerX, centerY, radius, borderPaint)

        // Draw the circular image
        val path = Path().apply {
            addCircle(centerX, centerY, radius - borderPaint.strokeWidth / 2, Path.Direction.CW)
        }
        canvas.clipPath(path)
        super.onDraw(canvas)
    }
}
