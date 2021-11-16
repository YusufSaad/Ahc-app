package com.example.coreandroid.sources.logging.destinations

import android.content.Context
import android.provider.Settings.Secure
import android.util.Log
import com.example.coreandroid.sources.dependencies.HasDependencies
import com.example.coreandroid.sources.extensions.merge
import com.example.coreandroid.sources.extensions.removeAll
import com.example.coreandroid.sources.network.HTTPServiceType
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

/**
 * Created by ahmedsaad on 2018-04-03.
 * Copyright © 2018. All rights reserved.
 */

class LogDNADestination(
        private val ingestionKey: String,
        private val hostName: String,
        private val appName: String,
        private val environment: String): HasDependencies, BaseDestination {

    companion object {
        private var payload = JSONArray()
    }

    private var ingestURL: String = "https://logs.logdna.com/logs/ingest?hostname=$hostName&now=${Date().time}"

    private val context: Context? by lazy {
        dependencies.resolveContext()
    }

    private var deviceIdentifier: String = Secure.getString(context?.contentResolver, Secure.ANDROID_ID)  ?: ""


    private val httpService: HTTPServiceType by lazy {
        dependencies.resolveHTTPService()
    }

    override fun send(level: Int, msg: String, file: String, function: String, line: Int, context: Any?): String? {
        val parameters = JSONObject()
        parameters.put("line", msg)

        parameters.put("level", {
            when (level) {
                Log.DEBUG, Log.VERBOSE ->"DEBUG"
                Log.INFO ->"INFO"
                Log.WARN ->"WARN"
                Log.ERROR -> "ERROR"
                else ->"DEBUG"
            }
        }())

        parameters.put("timestamp", Date().time)
        parameters.put("app", appName)
        parameters.put("env", environment)
        parameters.put("meta", {
            val codeMeta = JSONObject()
            codeMeta.put("device_id", deviceIdentifier)
            //codeMeta.put("advertising_id", advertisingIdentifier)
            codeMeta.put("code", {
                val code = JSONObject()
                code.put("fileName", file.split("/").lastOrNull() ?: "")
                code.put("function", function)
                code.put("line", line)
                code
            }())

            if (context as? JSONObject != null) {
                codeMeta.merge(context) {old, new -> old}
            }

             codeMeta
        }())

        // Send immediately
        flush(parameters)

        return parameters.toString()
    }

    private fun flush(parameters: JSONObject) {
        // Construct authorization
        val headers = mapOf(
                Pair("Content-Type", "application/json; charset=UTF-8"),
                Pair("apikey", ingestionKey)
        )

        // Construct parameters and handle queue
        val payload = JSONArray()
        payload.put(parameters)

        synchronized(LogDNADestination) {
            if (LogDNADestination.payload.length() > 0) {
                (0 until LogDNADestination.payload.length()).forEach {
                    payload.put(LogDNADestination.payload.get(it))
                }
                LogDNADestination.payload.removeAll()
            }
        }

        val body = JSONObject()
        body.put("lines", payload)

        // Send remotely
        httpService.post(url = ingestURL, headers = headers, body = body.toString()) {
            if (it.isSuccess) { return@post }

            synchronized(LogDNADestination) {
                (0 until payload.length()).forEach {
                    LogDNADestination.payload.put(payload.get(it))
                }
            }
        }
    }
}