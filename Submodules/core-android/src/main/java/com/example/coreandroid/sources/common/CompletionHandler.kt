package com.example.coreandroid.sources.common

/**
* Created by ahmedsaad on 2017-10-24.
* Copyright © 2017. All rights reserved.
*/

data class CompletionResponse<T, E>(val isSuccess: Boolean = false, val value: T? = null, val error: E? = null) {
    companion object {
        fun <T, E>success(value: T? = null): CompletionResponse<T, E> {
            return CompletionResponse(true, value, null)
        }

        fun <T, E>failure(error: E? = null): CompletionResponse<T, E> {
            return CompletionResponse(false, null, error)
        }
    }
}

typealias Result<T, E> = (CompletionResponse<T, E>) -> Unit
