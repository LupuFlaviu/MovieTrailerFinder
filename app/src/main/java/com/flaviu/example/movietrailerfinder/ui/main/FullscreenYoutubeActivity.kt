package com.flaviu.example.movietrailerfinder.ui.main

import android.os.Bundle
import com.flaviu.example.movietrailerfinder.BuildConfig
import com.flaviu.example.movietrailerfinder.R
import com.flaviu.example.movietrailerfinder.utils.ui.YouTubeFailureRecoveryActivity
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_youtube.*


class FullscreenYoutubeActivity : YouTubeFailureRecoveryActivity() {

    private var player: YouTubePlayer? = null

    override val youTubePlayerProvider: YouTubePlayer.Provider
        get() = playerView!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_youtube)

        playerView!!.initialize(BuildConfig.YOUTUBE_API_KEY, this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider, player: YouTubePlayer,
        wasRestored: Boolean
    ) {
        this.player = player
        player.setFullscreen(true)
        if (!wasRestored) {
            val url = FullscreenYoutubeActivityArgs.fromBundle(intent.extras!!).url
            player.loadVideo(url.substring(url.indexOf("=") + 1, url.length))
        }
    }
}