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
open class CoreApplication: PluggableApplication() {
    private val broadcastReceiver = AppBroadcastReceiver()

    override fun onCreate() {
        super.onCreate()

        val filter = IntentFilter()

        if (services.any { it is AuthenticationServiceDelegate }) {
            filter.addAction(ACTION_AUTHENTICATION_DID_LOGIN)
            filter.addAction(ACTION_AUTHENTICATION_DID_LOGOUT)
        }

        filter.addAction(ACTION_REFRESHED_DEVICE_TOKEN)

        LocalBroadcastManager.getInstance(this.applicationContext)
                .registerReceiver(broadcastReceiver, filter)
    }

    override fun onTerminate() {
        super.onTerminate()
        LocalBroadcastManager.getInstance(this.applicationContext)
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

    fun onTokenRefresh(deviceToken: String) {
        if (deviceToken.isEmpty()) return
        services.mapNotNull { it as? PushNotificationApplicationServicable }
                .forEach { it.onTokenRefresh(deviceToken = deviceToken) }
    }

    inner class AppBroadcastReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            callOnBackgroundThread {
                when (intent.action) {
                    ACTION_AUTHENTICATION_DID_LOGIN -> authenticationDidLogin(intent.getIntExtra(USER_ID, -1))
                    ACTION_AUTHENTICATION_DID_LOGOUT -> authenticationDidLogout(intent.getIntExtra(USER_ID, -1))
                    ACTION_REFRESHED_DEVICE_TOKEN -> onTokenRefresh(intent.getStringExtra(DEVICE_TOKEN))
                }
            }
        }
    }
}

interface PushNotificationApplicationServicable {
    fun onTokenRefresh(deviceToken: String)
}