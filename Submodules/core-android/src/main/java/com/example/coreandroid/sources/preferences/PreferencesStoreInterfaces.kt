package com.example.coreandroid.sources.preferences

import com.example.coreandroid.sources.enums.DefaultsKey

/**
 * Created by ahmedsaad on 2017-11-03.
 * Copyright © 2017. All rights reserved.
 */

interface PreferencesStore {
    val baseURL: String
    val baseREST: String
    val emailUserAdmin: Int
    val facebookShareURL: Int
    val supportEmailBody: Int
    val forgotPasswordURL: String
    val firebaseURL: String
    val youtubeURL: String
    val blogURL: String
    val blogRSS: String
    val isAnonymousEnabled: Boolean

    fun <T> get(key: DefaultsKey<T?>): T?
    fun <T> set(value: T?, key: DefaultsKey<T?>)
    fun <T> remove(key: DefaultsKey<T?>)
    fun clear()
}

abstract class PreferencesWorkerType: PreferencesStore