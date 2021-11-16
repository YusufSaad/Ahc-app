package com.example.coreandroid.sources.errors

/**
 * Created by ahmedsaad on 2017-10-24.
 */

sealed class DataError : Exception() {
    object DuplicateFailure : DataError()
    object NonExistent : DataError()
    object InComplete : DataError()
    object Unauthorized : DataError()
    object NoInternet : DataError()
    class ParseFailure(var error: Exception?) : DataError()
    class DatabaseFailure(var error: Exception?) : DataError()
    class CacheFailure(var error: Exception?) : DataError()
    class NetworkFailure(var error: Exception?) : DataError()
    class UnknownReason(var error: Exception?) : DataError()
    class Other(var errors: FieldErrors) : DataError()
}