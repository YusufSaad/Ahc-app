package com.example.coreandroid.sources.errors

import com.example.coreandroid.sources.extensions.scrubbed
import com.example.coreandroid.sources.logging.LogHelper
import okhttp3.Request
import org.json.JSONObject

/**
 * Created by ahmedsaad on 2017-11-09.
 * Copyright © 2017. All rights reserved.
 */

// Alias type used to represent specific field errors from the server
typealias FieldErrors = MutableMap<String, ArrayList<String>>

/// The NetworkError type represents an error object returned from the API server.
class NetworkError(val urlRequest: Request? = null,
                   val statusCode: Int = 0,
                   val headerValues: Map<String, String> = mutableMapOf(),
                   var fieldErrors: FieldErrors = mutableMapOf(),
                   val serverData: String? = null,
                   val internalError: Exception? = null) : Exception() {


    /// The initializer for the NetworkError type.
    ///
    /// - Parameters:
    ///   - statusCode: Status code of the network response.
    ///   - serverData: The data from the server that contains the error and corresponding fields.
    ///   - internalError: The internal error type from the network request.
    init {
        // Convert data to field error types
        this.fieldErrors = getFieldErrors(serverData)
    }

    private fun getFieldErrors(response: String?) : MutableMap<String, ArrayList<String>> {
        val fieldErrors: FieldErrors = mutableMapOf()

        try {
            response?.apply {
                val errorJSON = JSONObject(this)
                val errorsObject = errorJSON.optJSONObject("errors")
                if (errorsObject != null) {
                    val names = errorsObject.names()

                    for (i in 0 until names.length()) {
                        val key = names.getString(i)

                        val errorMessages = errorsObject.optJSONArray(key)
                        if (errorMessages != null) {
                            val errorArray = arrayListOf<String>()
                            for (j in 0 until errorMessages.length()) {
                                errorArray.add(errorMessages.getString(j))
                            }
                            fieldErrors[key] = errorArray
                        }
                    }
                }
            }
        } catch (e: Exception) {
            LogHelper.d(messages = *arrayOf("An error occurred while converting HTTP response " +
                    "${response ?: ""} to JSON: ${e.localizedMessage ?: ""}"))
        }

        return fieldErrors
    }

    val description: String
        get() {
            return "${internalError ?: DataError.UnknownReason(null)}\n" +
                    "        Request: {\n" +
                    "            url: ${urlRequest?.url()?.url()?.toString() ?: ""},\n" +
                    "            method: ${urlRequest?.method() ?: ""},\n" +
                    "            headers: ${urlRequest?.headers()?.toMultimap()?.scrubbed ?: ""},\n" +
                    "        },\n" +
                    "        Response: {\n" +
                    "            status: $statusCode,\n" +
                    "            body: $fieldErrors,\n" +
                    "            headers: ${headerValues.scrubbed}\n" +
                    "        }"
        }
}

