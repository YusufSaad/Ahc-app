package com.example.coreandroid.sources.controls

import android.app.Activity
import android.app.Application
import android.content.res.Configuration
import android.os.Bundle

/**
 * Created by ahmedsaad on 2018-02-06.
 * Copyright © 2017. All rights reserved.
 */
interface ApplicationService {
    fun onCreate() {}
    fun onTerminate() {}
    fun onConfigurationChanged(newConfig: Configuration?) {}

    fun onActivityStarted(activity: Activity?) {}
    fun onActivityStopped(activity: Activity?) {}
    fun onActivityPaused(activity: Activity?) {}
    fun onActivityResumed(activity: Activity?) {}
    fun onActivityDestroyed(activity: Activity?) {}
    fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) {}
    fun onActivityCreated(activity: Activity?, bundle: Bundle?) {}
}

open class PluggableApplication: Application.ActivityLifecycleCallbacks, Application() {

    open lateinit var services: ArrayList<ApplicationService>

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)

        services.forEach { it.onCreate() }
    }

    override fun onTerminate() {
        super.onTerminate()
        services.forEach { it.onTerminate() }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        services.forEach { it.onConfigurationChanged(newConfig) }
    }

    override fun onActivityPaused(p0: Activity?) {
        services.forEach { it.onActivityPaused(p0) }
    }

    override fun onActivityResumed(p0: Activity?) {
        services.forEach { it.onActivityResumed(p0) }
    }

    override fun onActivityStarted(p0: Activity?) {
        services.forEach { it.onActivityStarted(p0) }
    }

    override fun onActivityDestroyed(p0: Activity?) {
        services.forEach { it.onActivityDestroyed(p0) }
    }

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
        services.forEach { it.onActivitySaveInstanceState(p0, p1) }
    }

    override fun onActivityStopped(p0: Activity?) {
        services.forEach { it.onActivityStopped(p0) }
    }

    override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
        services.forEach { it.onActivityCreated(p0, p1) }
    }
}