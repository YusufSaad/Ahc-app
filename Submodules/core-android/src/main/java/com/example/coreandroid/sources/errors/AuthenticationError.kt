package com.example.coreandroid.sources.errors

/**
* Created by ahmedsaad on 2017-10-24.
* Copyright © 2017. All rights reserved.
*/

sealed class AuthenticationError : Exception() {
    object IncorrectCredentials : AuthenticationError()
    object Incomplete : AuthenticationError()
    object NoInternet : AuthenticationError()
    object BadRequest : AuthenticationError()
    class Other(var errors: FieldErrors) : AuthenticationError()
    class ParseFailure(var error: Exception?) : AuthenticationError()
    class UnknownReason(var error: Exception?) : AuthenticationError()
}