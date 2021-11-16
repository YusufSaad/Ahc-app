package org.abuhuraira.app.common.controls.customTabs

import android.app.Activity
import android.content.Intent
import android.net.Uri

/**
 * A Fallback that opens a Webview when Custom Tabs is not available
 */
class WebviewFallback: CustomTabActivityHelper.CustomTabFallback {
    override fun openUri(activity: Activity, uri: Uri) {
        val intent = Intent(activity, WebviewActivity::class.java)
        intent.data = uri
        activity.startActivity(intent)
    }
}
