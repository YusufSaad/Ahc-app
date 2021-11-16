package com.example.coreandroid.sources.network

import android.net.Uri
import com.example.coreandroid.sources.enums.NetworkMethod
import com.example.coreandroid.sources.preferences.PreferencesWorkerType
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody

/**
 * Created by ahmedsaad on 2017-11-03.
 * Copyright © 2017. All rights reserved.
 */

abstract class APIRouter {
    companion object {
        val JSON = MediaType.parse("application/json; charset=utf-8")
    }

    abstract val method: NetworkMethod
    abstract val path: String
    open val requestBody: RequestBody? = null
    open val queryParameterList: MutableMap<String, Any>? = null

    fun asURLRequest(preferencesWorker: PreferencesWorkerType) : Request.Builder {
        val uri = Uri.parse(preferencesWorker.baseURL)
                .buildUpon()
                .appendEncodedPath(preferencesWorker.baseREST)
                .appendEncodedPath(path)

        queryParameterList?.forEach { uri.appendQueryParameter(it.key, it.value.toString()) }

        return Request.Builder()
                .url(uri.build().toString())
                .method(method.name, requestBody)
    }
}