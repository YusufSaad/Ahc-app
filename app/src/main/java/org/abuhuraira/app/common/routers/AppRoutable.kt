package org.abuhuraira.app.common.routers

import android.support.v4.app.Fragment
import org.abuhuraira.app.common.controls.customTabs.CustomTabs
import java.lang.ref.WeakReference

/**
 * Created by ahmedsaad on 2018-01-17.
 * Copyright © 2017. All rights reserved.
 */
interface AppRoutable {
    var fragment: WeakReference<Fragment?>

    fun dismiss(isFragment: Boolean = false) {
        if (isFragment) {
            fragment.get()?.fragmentManager?.popBackStack() ?: fragment.get()?.activity?.finish()
        } else {
            fragment.get()?.activity?.finish()
        }
    }

    /**
    Display an fullscreen webview in a convenient way.
     **/
    fun present(url: String) {
        val activity = fragment.get()?.activity ?: return
        CustomTabs().openTab(activity, url)
    }
}