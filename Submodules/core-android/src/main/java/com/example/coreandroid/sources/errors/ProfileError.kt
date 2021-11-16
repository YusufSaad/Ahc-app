package com.example.coreandroid.sources.errors

/**
* Created by ahmedsaad on 2017-10-24.
* Copyright © 2017. All rights reserved.
*/

sealed class ProfileError : Exception() {
    object InvalidEmail : ProfileError()
    object PasswordMismatch : ProfileError()
    object PasswordStrength : ProfileError()
    object Incomplete : ProfileError()
    object NonExistent : ProfileError()
    object NoInternet : ProfileError()
    object BadRequest : ProfileError()
    object Unauthorized : ProfileError()
    class ParseFailure(var error: Exception?) : ProfileError()
    class DatabaseFailure(var error: Exception?) : ProfileError()
    class CacheFailure(var error: Exception?) : ProfileError()
    class UnknownReason(var error: Exception?) : ProfileError()
    class Other(var errors: FieldErrors) : ProfileError()
}