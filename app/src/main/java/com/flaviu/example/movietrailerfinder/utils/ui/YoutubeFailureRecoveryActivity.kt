package com.flaviu.example.movietrailerfinder.utils.ui

import android.content.Intent
import android.widget.Toast
import com.flaviu.example.movietrailerfinder.BuildConfig
import com.flaviu.example.movietrailerfinder.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

abstract class YouTubeFailureRecoveryActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    companion object {
        private const val RECOVERY_DIALOG_REQUEST = 1
    }

    protected abstract val youTubePlayerProvider: YouTubePlayer.Provider

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider,
        errorReason: YouTubeInitializationResult
    ) {
        if (errorReason.isUserRecoverableError) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
        } else {
            val errorMessage = String.format(getString(R.string.error_player), errorReason.toString())
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            youTubePlayerProvider.initialize(BuildConfig.YOUTUBE_API_KEY, this)
        }
    }
}