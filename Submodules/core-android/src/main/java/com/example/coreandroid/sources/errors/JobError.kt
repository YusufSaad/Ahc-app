package com.example.coreandroid.sources.errors

/**
 * Created by ahmedsaad on 2018-01-03.
 * Copyright © 2017. All rights reserved.
 */
sealed class JobError: Exception() {
    object IncompleteDescription: JobError()
    object NoSelectedAddress: JobError()
    object NoDateRequests: JobError()
    object NonExistent: JobError()
    object NoInternet: JobError()
    object BadRequest: JobError()
    object Unauthorized: JobError()
    class Other(val errors: FieldErrors): JobError()
    class ProcessingFailure(val error: Exception?): JobError()
    class DatabaseFailure(val error: Exception?): JobError()
    class CacheFailure(val error: Exception?): JobError()
    class ParseFailure(val error: Exception?): JobError()
    class UnknownReason(val error: Exception?): JobError()
}