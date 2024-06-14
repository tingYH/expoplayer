package com.example.exoplayerdemo.wedget

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import com.example.exoplayerdemo.callback.Player
import okhttp3.OkHttpClient
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException

class ExoPlayer : Player {
    private lateinit var exoPlayer: ExoPlayer

    @OptIn(UnstableApi::class)
    override fun init(context: Context, uri: Uri, view: View) {
        exoPlayer = ExoPlayer.Builder(context).build().apply {
            val client = createUnsafeOkHttpClient()
            val dataSourceFactory = OkHttpDataSource.Factory(client)
            val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(uri))
            setMediaSource(mediaSource)
            addListener(object : androidx.media3.common.Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    super.onPlayerError(error)
                    Log.e("ExoPlayer", "player uri $uri\nerror:　$error")
                }
            })
            (view as? PlayerView)?.let { playerView ->
                playerView.player = this
                prepare()
            }
        }
    }

    override fun play() {
        if (!exoPlayer.isPlaying) {
            exoPlayer.play()
        }
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun stop() {
        if (exoPlayer.isPlaying) {
            exoPlayer.stop()
        }
    }

    override fun setSource(uri: Uri) {
        exoPlayer.setMediaItem(MediaItem.fromUri(uri))
    }

    override fun release() {
        if (exoPlayer.isPlaying) {
            exoPlayer.stop()
        }
        exoPlayer.release()
    }

    private fun createUnsafeOkHttpClient(): OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            val sslSocketFactory = sslContext.socketFactory

            return OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier(HostnameVerifier { _, _ -> true })
                .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}