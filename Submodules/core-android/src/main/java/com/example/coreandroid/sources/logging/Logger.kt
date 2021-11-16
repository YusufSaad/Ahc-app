package com.example.coreandroid.sources.logging

import android.content.Context
import android.os.Build
import com.example.coreandroid.sources.ConstantsType
import com.example.coreandroid.sources.dependencies.HasDependencies
import com.example.coreandroid.sources.enums.DefaultsKeys.Companion.userID
import com.example.coreandroid.sources.enums.Environment
import com.example.coreandroid.sources.logging.destinations.LogDNADestination
import com.example.coreandroid.sources.preferences.PreferencesWorkerType
import org.json.JSONObject

/**
 * Created by ahmedsaad on 2017-11-06.
 * Copyright © 2017. All rights reserved.
 */

object Logger: HasDependencies {

    private val preferencesWorker: PreferencesWorkerType by lazy {
        dependencies.resolvePreferencesWorker()
    }

    private val constants: ConstantsType by lazy {
        dependencies.resolveConstants()
    }

    private val context: Context? by lazy {
        dependencies.resolveContext()
    }

    val environment = Environment.mode

    private val systemVersion: String by lazy {
        "${Build.VERSION.SDK_INT} (${Build.VERSION.RELEASE})"
    }

    lateinit var version: String

    private val deviceModel: String by lazy {
        "${Build.MANUFACTURER} ${Build.BRAND} ${Build.MODEL}"
    }

    fun setupLogger(appVersion: String?, appBuild: Int?) {
        this.version = "${appVersion ?: "-"} (${appBuild ?: "-"})"
        setUpCloud()
    }

    private fun setUpCloud() {
        LogHelper.destinations.add(
                LogDNADestination(
                        ingestionKey = constants.logDNAKey,
                        hostName = "Android",
                        appName = context?.applicationInfo?.loadLabel(context?.packageManager).toString(),
                        environment = Environment.mode.name.toLowerCase().capitalize()
                )
        )
    }

    /// Meta data to append to the log
    val metaLog: JSONObject
        get() {
            val output = JSONObject()

            output.put("user_id", preferencesWorker.get(userID) ?: 0)
            output.put("app_version", version)
            output.put("system_version", systemVersion)
            output.put("device_model", deviceModel)
            output.put("environment", environment.name)

            // TODO: Add application state to metadata
            /*if let application = Logger.application {
                output["application_state"] = {
                    switch application.applicationState {
                        case .active: return "active"
                        case .background: return "background"
                        case .inactive: return "inactive"
                    }
                }()

                output["protected_data_available"] = application.isProtectedDataAvailable
            }*/

            return output
        }
}