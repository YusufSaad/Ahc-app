package com.example.coreandroid.sources.common

import android.content.Context
import android.net.ConnectivityManager


/**
 * Created by ahmedsaad on 2017-11-16.
 * Copyright © 2017. All rights reserved.
 */

fun isNetworkAvailable(context: Context?): Boolean {
    if (context == null)
        return false

    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
}