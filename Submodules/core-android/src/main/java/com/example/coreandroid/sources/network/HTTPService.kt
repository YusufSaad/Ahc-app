package com.example.coreandroid.sources.network

import android.net.Uri
import com.example.coreandroid.sources.common.CompletionResponse.Companion.failure
import com.example.coreandroid.sources.common.CompletionResponse.Companion.success
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.enums.NetworkMethod
import com.example.coreandroid.sources.errors.NetworkError
import com.example.coreandroid.sources.network.APIRouter.Companion.JSON
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by ahmedsaad on 2018-02-08.
 * Copyright © 2017. All rights reserved.
 */

data class ServerResponse (val data: String, val headers: Map<String, String>, val statusCode: Int)

interface HTTPServiceType {
    fun post(url: String, parameters: Map<String, Any?>? = null, body: String? = "", headers: Map<String, String>? = null,
             completion: Result<ServerResponse, NetworkError>)
    fun get(url: String, parameters: Map<String, Any?>? = null, headers: Map<String, String>? = null,
             completion: Result<ServerResponse, NetworkError>)
}

class HTTPService: HTTPServiceType {
    private val sessionManager : OkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()

    override fun post(url: String, parameters: Map<String, Any?>?, body: String?, headers: Map<String, String>?,
                      completion: Result<ServerResponse, NetworkError>) {
        val uri = Uri.parse(url)
                .buildUpon()

        try {
            parameters?.forEach { if (it.value != null) uri.appendQueryParameter(it.key, it.value.toString()) }
        } catch (error: Exception) {
            return completion(failure(
                    NetworkError(
                            statusCode = 0,
                            internalError = error
                    )
            ))
        }

        val urlRequest = Request.Builder()
                .url(uri.build().toString())
                .method(NetworkMethod.POST.name, RequestBody.create(JSON, body ?: ""))

        headers?.forEach {
            urlRequest.addHeader(it.key, it.value)
        }

        sessionManager.request(urlRequest.build(), completion = completion)
    }

    override fun get(url: String, parameters: Map<String, Any?>?, headers: Map<String, String>?,
                      completion: Result<ServerResponse, NetworkError>) {
        val uri = Uri.parse(url)
                .buildUpon()

        try {
            parameters?.forEach { if (it.value != null) uri.appendQueryParameter(it.key, it.value.toString()) }
        } catch (error: Exception) {
            return completion(failure(
                    NetworkError(
                            statusCode = 0,
                            internalError = error
                    )
            ))
        }

        val urlRequest = Request.Builder()
                .url(uri.build().toString())
                .method(NetworkMethod.GET.name, null)

        headers?.forEach {
            urlRequest.addHeader(it.key, it.value)
        }

        sessionManager.request(urlRequest.build(), completion = completion)
    }
}

/// Creates a network request to retrieve the contents of a URL based on the specified urlRequest.
///
/// - Parameters:
///   - urlRequest: The URL request.
///   - completion: A handler to be called once the request has finished.
fun OkHttpClient.request(urlRequest: Request, completion: Result<ServerResponse, NetworkError>) {
    this
            .newCall(urlRequest)
            .enqueue(object: Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    // Log request/response debugging
                    completion(failure(
                            NetworkError(urlRequest = urlRequest, internalError = e)
                    ))
                }

                override fun onResponse(call: Call?, response: Response?) {

                    // Retrieve the data
                    val data = response?.body()?.string()

                    if (response != null) {
                        // Convert header values to string dictionary
                        val headers = response.headers().toMultimap().mapValues { it.value.first() }
                        val statusCode = response.code()

                        if (response.isSuccessful) {
                            completion(success(
                                    ServerResponse(
                                            data = data ?: "",
                                            headers = headers,
                                            statusCode = statusCode
                                    )
                            ))
                        } else {
                            val error = NetworkError(
                                    urlRequest = urlRequest,
                                    statusCode = statusCode,
                                    headerValues = headers,
                                    serverData = data ?: "")
                            completion(failure(error))
                        }
                    } else {
                        completion(failure(NetworkError(statusCode = 400)))
                    }
                }
            })
}