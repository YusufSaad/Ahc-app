package com.example.coreandroid.sources.errors

/**
 * Created by ahmedsaad on 2018-01-04.
 * Copyright © 2017. All rights reserved.
 */
sealed class PaymentError: Exception() {
    object InvalidNumber : PaymentError()
    object InvalidCVV : PaymentError()
    object ExpiredDate : PaymentError()
    object Incomplete: PaymentError()
    object NoInternet: PaymentError()
    object BadRequest: PaymentError()
    object Unauthorized: PaymentError()
    class Other(val errors: FieldErrors): PaymentError()
    class ProcessingFailure(val error: Exception?): PaymentError()
    class DatabaseFailure(val error: Exception?): PaymentError()
    class CacheFailure(val error: Exception?): PaymentError()
    class ParseFailure(val error: Exception?): PaymentError()
    class UnknownReason(val error: Exception?): PaymentError()
}