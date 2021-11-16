package org.abuhuraira.app.common.controls

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.support.v7.app.AlertDialog
import org.abuhuraira.app.R
import org.abuhuraira.app.common.extensions.getString
import com.example.coreandroid.sources.logging.LogHelper

/**
 * Created by fernando for EasyRatingDialog:
 * https://github.com/fernandodev/easy-rating-dialog
 *
 * Usage:
 * 1) private val playRatingDialog by lazy { PlayRatingDialog(this.activity as Context) }
 * 2) override fun onStart() = playRatingDialog.onStart()
 * 3) private fun loadUI() = playRatingDialog.showIfNeeded()
 *
 * TODO:
 * 1) Refactor
 * 2) Open Google Play AND ONLY Google Play?: https://stackoverflow.com/a/28090925
 * 3) Design and string resource
 * 4) Remove res/constants.xml
 *
 */
class PlayRatingDialog(private val mContext: Context?) {

    companion object {
        private val PREFS_NAME = "play_rating_dialog"
        private val KEY_WAS_RATED = "KEY_WAS_RATED"
    }

    private val mPreferences: SharedPreferences? = mContext?.getSharedPreferences(PREFS_NAME, 0)
    private var mCondition: ConditionTrigger? = null
    private var mDialog: Dialog? = null

    val isShowing: Boolean
        get() = mDialog != null && mDialog?.isShowing == true

    interface ConditionTrigger {
        fun shouldShow(): Boolean
    }

    fun showAnyway() {
        tryShow()
    }

    fun showIfNeeded() {
        if (mCondition != null) {
            if (mCondition?.shouldShow() == true)
                tryShow()
        } else {
            if (shouldShow())
                tryShow()
        }
    }

    fun rateNow() {
        val appPackage = mContext?.packageName
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/" +
                "apps/details?id=${appPackage ?: getString(R.string.package_name)}"))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        mContext?.startActivity(intent)
        mPreferences?.edit()?.putBoolean(KEY_WAS_RATED, true)?.apply()
    }

    fun didRate(): Boolean {
        return mPreferences?.getBoolean(KEY_WAS_RATED, false) ?: false
    }

    fun setConditionTrigger(condition: ConditionTrigger) {
        mCondition = condition
    }

    fun setCancelable(cancelable: Boolean) {
        mDialog?.setCancelable(cancelable)
    }

    private fun tryShow() {
        if (isShowing)
            return

        try {
            mDialog = null
            mDialog = createDialog()
            mDialog?.show()
        } catch (e: Exception) {
            //It prevents many Android exceptions
            //when user interactions conflicts with UI thread or Activity expired window token
            //BadTokenException, IllegalStateException ...
            LogHelper.e(messages = *arrayOf(e.localizedMessage))
        }

    }

    private fun shouldShow(): Boolean {
        return !didRate()
    }

    private fun createDialog(): android.app.Dialog? {
        val context = mContext ?: return null
        return AlertDialog.Builder(context)
                .setTitle(R.string.play_rating_title)
                .setMessage(R.string.play_rating_message)
                .setNegativeButton(R.string.play_rating_no_thanks) { dialogInterface, i -> }
                .setPositiveButton(R.string.play_rating_rate_now) { dialogInterface, i -> rateNow() }
                .setOnCancelListener { }.create()
    }
}