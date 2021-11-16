package org.abuhuraira.app.scenes.showVideo

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MenuItem
import android.widget.LinearLayout
import com.example.ahcstores.sources.dependencies.HasDependencies
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_show_video.*
import org.abuhuraira.app.R
import org.abuhuraira.app.common.VIDEO_ID
import org.abuhuraira.app.common.protocols.NavigationInterface
import org.abuhuraira.app.scenes.showVideo.common.ShowVideoDisplayable

/**
 * Created by ahmedsaad on 2018-01-22.
 * Copyright © 2017 Jiffy Inc. All rights reserved.
 */
class ShowVideoActivity : YouTubeBaseActivity(), NavigationInterface, ShowVideoDisplayable, HasDependencies,
        YouTubePlayer.OnInitializedListener {

    companion object {
        const val RECOVERY_DIALOG_REQUEST = 1
    }

    private val constants by lazy {
        dependencies.resolveConstants()
    }

    lateinit var id: String
    private var youTubePlayer: YouTubePlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_show_video)

        if (savedInstanceState == null) {
            val videoID = try { intent.getStringExtra(VIDEO_ID) } catch (e: Exception) {null}
            if (videoID != null) {
                id = videoID
                playerView.initialize(constants.youtubeAPIKey, this)
            } else {
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        youTubePlayer?.setFullscreen(true)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        youTubePlayer?.setFullscreen(true)
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?,
                                         wasRestored: Boolean) {
        this.youTubePlayer = player
        this.youTubePlayer?.setFullscreen(true)
        this.youTubePlayer?.setShowFullscreenButton(false)

        if (!wasRestored) {
            this.youTubePlayer?.loadVideo(id)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, errorReason: YouTubeInitializationResult?) {
        if (errorReason?.isUserRecoverableError == true) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
        } else {
            val errorMessage = String.format(getString(R.string.error_player), errorReason.toString())
            Snackbar.make(playerView, errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            playerView.initialize(constants.youtubeAPIKey, this)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}