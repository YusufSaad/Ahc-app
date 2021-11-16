package com.example.coreandroid.sources.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.coreandroid.R
import com.example.coreandroid.sources.enums.DefaultsKey
import com.example.coreandroid.sources.enums.Environment.*
import com.example.coreandroid.sources.enums.Environment

/**
 * Created by ahmedsaad on 2017-11-03.
 * Copyright © 2017. All rights reserved.
 */

class PreferencesResourceStore(val context: Context?): PreferencesStore {
    private val defaults: SharedPreferences? = context?.
            getSharedPreferences("user_defaults", Context.MODE_PRIVATE)

    override val baseURL: String
        get() = when (Environment.mode) {
            DEVELOPMENT -> context?.getString(R.string.base_url_debug) ?: ""
            PRODUCTION -> context?.getString(R.string.base_url) ?: ""
        }

    override val baseREST: String
        get() = context?.getString(R.string.base_rest) ?: ""

    override val emailUserAdmin: Int
        get() = when (Environment.mode) {
            DEVELOPMENT -> R.string.email_user_admin_debug
            PRODUCTION -> R.string.email_user_admin
        }

    override val facebookShareURL: Int
        get() = R.string.facebook_share_url

    override val supportEmailBody: Int
        get() = R.string.support_email_body

    override val forgotPasswordURL: String
        get() = baseURL + context?.getString(R.string.forgot_password_path)

    override val firebaseURL: String
        get() = when (Environment.mode) {
            DEVELOPMENT -> context?.getString(R.string.firebase_url_debug) ?: ""
            PRODUCTION -> context?.getString(R.string.firebase_url) ?: ""
        }

    override val youtubeURL: String
        get() = context?.getString(R.string.youtube_data_url) ?: ""

    override val blogURL: String
        get() = context?.getString(R.string.blog_url) ?: ""

    override val blogRSS: String
        get() = context?.getString(R.string.blog_rss) ?: ""

    override val isAnonymousEnabled: Boolean
        get() = context?.getString(R.string.is_anonymous_enabled)?.toBoolean() ?: false

    /// Retrieves the value from user defaults that corresponds to the given key.
    ///
    /// - Parameter key: The key that is used to read the user defaults item.
    override fun <T> get(key: DefaultsKey<T?>): T? {
        return defaults?.get(key)
    }

    /// Stores the value in the user defaults item under the given key.
    ///
    /// - Parameters:
    ///   - value: Value to be written to the user defaults.
    ///   - key: Key under which the value is stored in the user defaults.
    override fun <T> set(value: T?, key: DefaultsKey<T?>) {
        defaults?.put(value = value, key = key)
    }

    /// Deletes the single user defaults item specified by the key.
    ///
    /// - Parameter key: The key that is used to delete the keychain item.
    /// - Returns: True if the item was successfully deleted.
    override fun <T> remove(key: DefaultsKey<T?>) {
        val editor = defaults?.edit()
        editor?.remove(key.name)
        editor?.apply()
    }

    /// Removes all the user defaults items.
    override fun clear() {
        val editor = defaults?.edit()
        editor?.clear()
        editor?.apply()
    }

}

@Suppress("UNCHECKED_CAST")
fun <T> SharedPreferences.get(key: DefaultsKey<T?>): T? {
    return when (key.type) {
        is String? -> this.getString(key.name, null) as T?
        is Int? -> {
            val value = this.getInt(key.name, -1) as T?
            if (value == -1) null else value
        }
        is Float? -> {
            val value = this.getFloat(key.name, -1F) as T?
            if (value == -1F) null else value
        }
        is Long? -> {
            val value = this.getLong(key.name, -1L) as T?
            if (value == -1L) null else value
        }
        is Boolean? -> this.getBoolean(key.name, false) as T?
        else -> null
    }
}

fun <T> SharedPreferences.put(value: T?, key: DefaultsKey<T?>) {
    val editor = this.edit()
    when (value) {
        is String? -> editor.putString(key.name, value as String)
        is Int? -> editor.putInt(key.name, value as Int)
        is Float? -> editor.putFloat(key.name, value as Float)
        is Long? -> editor.putLong(key.name, value as Long)
        is Boolean? -> editor.putBoolean(key.name, value as Boolean)
        else -> {}
    }
    editor.apply()
}