package com.example.coreandroid.sources.extensions

import bolts.Continuation
import bolts.Task
import bolts.TaskCompletionSource
import com.example.coreandroid.sources.common.CompletionResponse
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.errors.DataError
import com.example.coreandroid.sources.errors.PaymentError
import com.example.coreandroid.sources.errors.ProfileError
import com.example.coreandroid.sources.logging.LogHelper
import io.realm.Realm
import java.util.concurrent.Callable
import java.util.concurrent.Executor

/**
 * Created by ahmedsaad on 2018-02-14.
 * Copyright © 2017. All rights reserved.
 */


/**
 * Reverse executor and continuation
 */
fun <TResult, TContinuationResult> Task<TResult>.continueWith(executor: Executor,
                                                              continuation: (task: Task<TResult>) -> TContinuationResult): Task<TContinuationResult> {
    return continueWith(Continuation<TResult, TContinuationResult> {continuation(it)}, executor)
}

/**
 * Auto runs on UI
 */
fun <TResult, TContinuationResult> Task<TResult>.continueOnUiWith(continuation: (task: Task<TResult>) -> TContinuationResult): Task<TContinuationResult> {
    return continueWith(Continuation<TResult, TContinuationResult> {continuation(it)}, Task.UI_THREAD_EXECUTOR)
}

fun <TResult, TContinuationResult> Task<TResult>.continueOnBackgroundWith(continuation: (task: Task<TResult>) -> TContinuationResult): Task<TContinuationResult> {
    return continueWith(Continuation<TResult, TContinuationResult> {continuation(it)}, Task.BACKGROUND_EXECUTOR)
}


fun <T, E> Task<Task<T>>.continueOnUiWithCompletion(completion: Result<T, E>?) {
    continueOnUiWith {
        val task: Task<T> = it.result

        if (task.isCompleted)
            completion?.invoke(CompletionResponse(task.error == null, task.result, task.error as? E))
    }
}

fun <T, E> Task<Task<T>>.continueWithCompletion(completion: Result<T, E>?) {
    continueOnBackgroundWith {
        val task: Task<T> = it.result

        if (task.isCompleted)
            completion?.invoke(CompletionResponse(task.error == null, task.result, task.error as? E))
    }
}

fun <T, E> callInBackgroundWithCompletionOnUi(completion: Result<T, E>,
                                              call: (tcs: TaskCompletionSource<T>) -> Unit) {
    Task.callInBackground {
        val tcs = TaskCompletionSource<T>()
        call(tcs)
        tcs.task
    }.continueOnUiWithCompletion(completion)
}

fun <T, E> callInBackgroundWithCompletion(completion: Result<T, E>,
                                          call: (tcs: TaskCompletionSource<T>) -> Unit) {
    Task.callInBackground {
        val tcs = TaskCompletionSource<T>()
        call(tcs)
        tcs.task
    }.continueWithCompletion(completion)
}

inline fun <T, reified E> callRealmInBackgroundWithCompletionOnUi(noinline completion: Result<T, E>?,
                                                                  crossinline call: (realm: Realm, tcs: TaskCompletionSource<T>) -> Unit) {
    Task.callInBackground {
        val tcs = TaskCompletionSource<T>()
        var realm: Realm? = null

        try {
            realm = Realm.getDefaultInstance()
            call(realm, tcs)
        }  catch (error: Exception) {
            LogHelper.e(messages = *arrayOf("Unhandled error occurred: ${error.localizedMessage}."))
            val e = when (E::class.java) {
                is DataError -> DataError.DatabaseFailure(error)
                is PaymentError -> PaymentError.DatabaseFailure(error)
                is ProfileError -> ProfileError.DatabaseFailure(error)
                else -> null
            }
            tcs.setError(e)
        } finally {
            realm?.close()
        }
        tcs.task
    }.continueOnUiWithCompletion(completion)
}

inline fun <T, reified E> callRealmWithCompletion(noinline completion: Result<T, E>?,
                                                  crossinline call: (realm: Realm, tcs: TaskCompletionSource<T>) -> Unit) {
    Task.callInBackground {
        val tcs = TaskCompletionSource<T>()
        var realm: Realm? = null

        try {
            realm = Realm.getDefaultInstance()
            call(realm, tcs)
        }  catch (error: Exception) {
            LogHelper.e(messages = *arrayOf("Unhandled error occurred: ${error.localizedMessage}."))
            val e = when (E::class.java) {
                is DataError -> DataError.DatabaseFailure(error)
                is PaymentError -> PaymentError.DatabaseFailure(error)
                is ProfileError -> ProfileError.DatabaseFailure(error)
                else -> null
            }
            tcs.setError(e)
        } finally {
            realm?.close()
        }
        tcs.task
    }.continueWithCompletion(completion)
}


fun callOnUiThread(call: () -> Unit) {
    Task.call(Callable{
        call()
    }, Task.UI_THREAD_EXECUTOR)
}

fun callOnBackgroundThread(call: () -> Unit) {
    Task.callInBackground(call)
}