package org.abuhuraira.app.common.extensions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.view.inputmethod.InputMethodManager
import org.abuhuraira.app.common.Application


/**
 * Created by ahmedsaad on 2018-02-06.
 * Copyright © 2017. All rights reserved.
 */
fun Context.getResourceID(type: ResourceType, idString: String?): Int {
    val idString = idString ?: return 0
    return resources.getIdentifier(idString, type.name, packageName)
}

enum class ResourceType {
    drawable,
    string
}

/**
 * @return Application's version code from the {@code PackageManager}.
 */
val Context.appVersionName: String
    get() {
        return try {
            val packageInfo = packageManager
                    .getPackageInfo(packageName, 0)
             packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            // Should never happen.
            ""
        }
    }


fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

fun pxToDp(px: Int): Int {
    return (px / Resources.getSystem().displayMetrics.density).toInt()
}

fun getString(id: Int, vararg formatArgs: Any) : String {
    Application.instance.apply {
        return if (formatArgs.isEmpty())
            this.getString(id)
        else
        // Use the spread operator to convert array to vararg
            this.getString(id, *formatArgs)
    }

    return ""
}

fun Activity.closeKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}