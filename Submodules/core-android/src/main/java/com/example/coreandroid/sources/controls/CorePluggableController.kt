package com.example.coreandroid.sources.controls

import android.support.v4.content.LocalBroadcastManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import com.example.coreandroid.sources.extensions.*
import com.example.coreandroid.sources.protocols.AuthenticationServiceDelegate


/**
 * Created by ahmedsaad on 2018-02-06.
 * Copyright © 2017. All rights reserved.
 */
open class CorePluggableController: PluggableController() {
    private val broadcastReceiver = AppBroadcastReceiver()

    override fun onResume() {
        super.onResume()

        if (services.all { it !is AuthenticationServiceDelegate }) return
        val context = context ?: return

        val filter = IntentFilter()
        filter.addAction(ACTION_AUTHENTICATION_DID_LOGIN)
        filter.addAction(ACTION_AUTHENTICATION_DID_LOGOUT)

        LocalBroadcastManager.getInstance(context)
                .registerReceiver(broadcastReceiver, filter)
    }

    override fun onPause() {
        super.onPause()

        val context = context ?: return
        LocalBroadcastManager.getInstance(context)
                .unregisterReceiver(broadcastReceiver)
    }

    fun authenticationDidLogin(userID: Int) {
        if (userID == -1) return
        services.mapNotNull { it as? AuthenticationServiceDelegate }
                .forEach { it.authenticationDidLogin(userID = userID) }
    }

    fun authenticationDidLogout(userID: Int) {
        if (userID == -1) return
        services.mapNotNull { it as? AuthenticationServiceDelegate }
                .forEach { it.authenticationDidLogout(userID = userID) }
    }

    inner class AppBroadcastReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            callOnBackgroundThread {
                when (intent.action) {
                    ACTION_AUTHENTICATION_DID_LOGIN -> authenticationDidLogin(intent.getIntExtra(USER_ID, -1))
                    ACTION_AUTHENTICATION_DID_LOGOUT -> authenticationDidLogout(intent.getIntExtra(USER_ID, -1))
                }
            }
        }
    }
}