package com.flaviu.example.movietrailerfinder.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.flaviu.example.movietrailerfinder.BuildConfig
import com.flaviu.example.movietrailerfinder.R
import com.flaviu.example.movietrailerfinder.databinding.ActivityYoutubeBinding
import com.flaviu.example.movietrailerfinder.utils.ui.YouTubeFailureRecoveryActivity
import com.google.android.youtube.player.YouTubePlayer

class FullscreenYoutubeActivity : YouTubeFailureRecoveryActivity() {

    private var player: YouTubePlayer? = null
    private lateinit var binding: ActivityYoutubeBinding

    override val youTubePlayerProvider: YouTubePlayer.Provider
        get() = binding.playerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_youtube)

        binding.playerView.initialize(BuildConfig.YOUTUBE_API_KEY, this)
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