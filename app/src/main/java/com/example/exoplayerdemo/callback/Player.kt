package com.example.exoplayerdemo.callback

import android.content.Context
import android.net.Uri
import android.view.View

interface Player {
    fun init(context: Context, uri: Uri, view: View)
    fun play()
    fun pause()
    fun stop()
    fun release()
    fun setSource(uri: Uri)
}
