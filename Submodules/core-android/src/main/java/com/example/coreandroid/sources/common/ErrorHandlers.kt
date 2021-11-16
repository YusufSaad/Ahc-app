package com.example.coreandroid.sources.common

import com.example.coreandroid.sources.errors.*

/**
 * Created by ahmedsaad on 2018-01-03.
 * Copyright © 2017. All rights reserved.
 */

class NoInternetException: Exception()

fun initProfileError(error: NetworkError?): ProfileError {
    // Handle no internet
    if (error?.internalError is NoInternetException) {
        return ProfileError.NoInternet
    }

    return when(error?.statusCode) {
        400 -> ProfileError.BadRequest
        401 -> ProfileError.Unauthorized
        403 -> ProfileError.Unauthorized
        else -> ProfileError.Other(error?.fieldErrors ?: mutableMapOf())
    }
}

fun initAuthenticationError(error: NetworkError?): AuthenticationError {
    // Handle no internet
    if (error?.internalError is NoInternetException) {
        return AuthenticationError.NoInternet
    }

    return when(error?.statusCode) {
        400 -> AuthenticationError.BadRequest
        401 -> AuthenticationError.IncorrectCredentials
        else -> AuthenticationError.Other(error?.fieldErrors ?: mutableMapOf())
    }
}

fun initDataError(error: NetworkError?): DataError {
    // Handle no internet
    if (error?.internalError is NoInternetException) {
        return DataError.NoInternet
    }

    // Handle by status code
    return when (error?.statusCode) {
        400 -> DataError.NetworkFailure(error)
        401 -> DataError.Unauthorized
        else -> DataError.Other(error?.fieldErrors ?: mutableMapOf())
    }
}

fun initDataError(error: JobError?): DataError? {
    if (error == null)
        return null

    return when (error) {
        is JobError.IncompleteDescription -> DataError.InComplete
        is JobError.NoSelectedAddress -> DataError.InComplete
        is JobError.NoDateRequests -> DataError.InComplete
        is JobError.NonExistent -> DataError.NonExistent
        is JobError.NoInternet -> DataError.NoInternet
        is JobError.BadRequest -> DataError.InComplete
        is JobError.Unauthorized -> DataError.Unauthorized
        is JobError.Other -> DataError.Other(error.errors)
        is JobError.ProcessingFailure -> DataError.NetworkFailure(error.error)
        is JobError.DatabaseFailure -> DataError.DatabaseFailure(error.error)
        is JobError.CacheFailure -> DataError.CacheFailure(error.error)
        is JobError.ParseFailure -> DataError.ParseFailure(error.error)
        is JobError.UnknownReason -> DataError.UnknownReason(error.error)
    }
}

fun initDataError(error: ProfileError?): DataError? {
    if (error == null)
        return null

    return when (error) {
        is ProfileError.Incomplete -> DataError.InComplete
        is ProfileError.InvalidEmail -> DataError.InComplete
        is ProfileError.PasswordMismatch -> DataError.InComplete
        is ProfileError.PasswordStrength -> DataError.InComplete
        is ProfileError.NonExistent -> DataError.NonExistent
        is ProfileError.NoInternet -> DataError.NoInternet
        is ProfileError.BadRequest -> DataError.InComplete
        is ProfileError.Unauthorized -> DataError.Unauthorized
        is ProfileError.Other -> DataError.Other(error.errors)
        is ProfileError.DatabaseFailure -> DataError.DatabaseFailure(error.error)
        is ProfileError.CacheFailure -> DataError.CacheFailure(error.error)
        is ProfileError.ParseFailure -> DataError.ParseFailure(error.error)
        is ProfileError.UnknownReason -> DataError.UnknownReason(error.error)
    }
}

fun initJobError(error: NetworkError?): JobError {
    // Handle no internet
    if (error?.internalError is NoInternetException) {
        return JobError.NoInternet
    }

    // Handle by status code
    return when (error?.statusCode) {
        400 -> JobError.BadRequest
        401 -> JobError.Unauthorized
        else -> JobError.Other(error?.fieldErrors ?: mutableMapOf())
    }
}

fun initPaymentError(error: NetworkError?): PaymentError {
    // Handle no internet
    if (error?.internalError is NoInternetException) {
        return PaymentError.NoInternet
    }

    // Handle by status code
    return when (error?.statusCode) {
        400 -> PaymentError.BadRequest
        401 -> PaymentError.Unauthorized
        else -> PaymentError.Other(error?.fieldErrors ?: mutableMapOf())
    }
}