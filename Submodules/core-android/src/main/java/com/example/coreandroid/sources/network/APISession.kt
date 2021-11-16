package com.example.coreandroid.sources.network

import android.content.Context
import com.example.coreandroid.sources.common.CompletionResponse.Companion.failure
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.common.NoInternetException
import com.example.coreandroid.sources.common.isNetworkAvailable
import com.example.coreandroid.sources.enums.SecurityProperty.JWT
import com.example.coreandroid.sources.errors.NetworkError
import com.example.coreandroid.sources.extensions.callOnUiThread
import com.example.coreandroid.sources.extensions.scrubbed
import com.example.coreandroid.sources.logging.LogHelper
import com.example.coreandroid.sources.preferences.PreferencesWorkerType
import com.example.coreandroid.sources.security.SecurityWorkerType
import okhttp3.*
import java.util.concurrent.TimeUnit

/**
 * Created by ahmedsaad on 2017-11-03.
 * Copyright © 2017. All rights reserved.
 */

interface APISessionType {
    val isAuthorized: Boolean
    fun unauthorize()
    fun request(router: APIRouter, completion: Result<ServerResponse, NetworkError>)
}


internal class APISession(private val context: Context?,
                          private val preferencesWorker: PreferencesWorkerType,
                          private val securityWorker: SecurityWorkerType): APISessionType {

    private val sessionManager : OkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()


    /// Determine if user is authenticated with server
    override val isAuthorized: Boolean
        get() {
            // Assume user is logged in if they have a JWT token.
            // However, future requests can return a 401-unauthorized,
            // which should log user out and redirect to login screen.
            return !securityWorker.get(JWT).isNullOrBlank()
        }

    /// Remove stored tokens and credentials so requests will be unauthorized
    override fun unauthorize() {
        securityWorker.clear()
    }

    /// Creates a network request to retrieve the contents of a URL based on the specified router.
    ///
    /// - Parameters:
    ///   - router: The router request.
    ///   - completion: A handler to be called once the request has finished
    override fun request(router: APIRouter, completion: Result<ServerResponse, NetworkError>) {
        // Validate connectivity
        if (!isNetworkAvailable(context = context)) {
            return completion(failure(NetworkError(internalError = NoInternetException())))
        }

        val urlRequestBuilder: Request.Builder

        // Construct request
        try {
            urlRequestBuilder = router.asURLRequest(preferencesWorker)
            urlRequestBuilder.headers(APIAccessTokenAdapter(securityWorker).build())
        } catch (exception: Exception) {
            return completion(failure(NetworkError(internalError = exception)))
        }

        val urlRequest = urlRequestBuilder.build()

        // Log request/response debugging
        LogHelper.i(messages = *arrayOf("Request: $urlRequest"))

        sessionManager.request(urlRequest) {

            // Handle any pre-processing if applicable
            if (it.isSuccess && it.value != null) {
                LogHelper.d(messages = *arrayOf("Response: {\n\turl: ${urlRequest?.url()?.url()?.toString()}," +
                        "\n\tstatus: ${it.value.statusCode}, \n\theaders: ${it.value.headers.scrubbed}\n}"))
            } else if (it.error != null ){
                LogHelper.d(messages = *arrayOf("Network request error: ${it.error.description}"))
            }

            callOnUiThread {
                completion(it)
            }
        }
    }

    private fun APIVersionAdapter() = Headers.Builder().add("API-VERSION", "2018-04-12")

    private fun APIAccessTokenAdapter(securityWorker: SecurityWorkerType) : Headers.Builder {
        val headers = APIVersionAdapter()

        securityWorker.get(JWT)?.apply {
            if (!this.isBlank())
                headers.add("Authorization", "Token $this")
        }

        return headers
    }
}