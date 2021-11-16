package com.example.coreandroid.sources.preferences

import com.example.coreandroid.sources.enums.DefaultsKey

/**
 * Created by ahmedsaad on 2017-11-03.
 * Copyright © 2017. All rights reserved.
 */

class PreferencesWorker(val store: PreferencesStore) : PreferencesWorkerType() {

    /// Root URL of REST API sevice
    override val baseURL: String
        get() = store.baseURL

    /// Suffix segment of REST API service for users
    override val baseREST: String
        get() = store.baseREST

    /// Email of admin used for users
    override val emailUserAdmin: Int
        get() = store.emailUserAdmin

    /// URL template string for sharing on Facebook
    override val facebookShareURL: Int
        get() = store.facebookShareURL

    /// Email body template for support
    override val supportEmailBody: Int
        get() = store.supportEmailBody

    /// URL for forgot password
    override val forgotPasswordURL: String
        get() = store.forgotPasswordURL

    /// Root URL of Firebase functions sevice
    override val firebaseURL: String
        get() = store.firebaseURL

    /// Root URL of Youtube functions sevice
    override val youtubeURL: String
        get() = store.youtubeURL

    /// Blog URL of App
    override val blogURL: String
        get() = store.blogURL

    /// Blog RSS URL of App
    override val blogRSS: String
        get() =  store.blogRSS

    /// Enables anonymous access
    override val isAnonymousEnabled: Boolean
        get() = store.isAnonymousEnabled


    /// Retrieves the value from user defaults that corresponds to the given key.
    ///
    /// - Parameter key: The key that is used to read the user defaults item.
    override fun <T> get(key: DefaultsKey<T?>): T? {
        return store.get(key)
    }

    /// Stores the value in the user defaults item under the given key.
    ///
    /// - Parameters:
    ///   - value: Value to be written to the user defaults.
    ///   - key: Key under which the value is stored in the user defaults.
    override fun <T> set(value: T?, key: DefaultsKey<T?>) {
        store.set(value, key = key)
    }

    /// Deletes the single user defaults item specified by the key.
    ///
    /// - Parameter key: The key that is used to delete the keychain item.
    /// - Returns: True if the item was successfully deleted.
    override fun <T> remove(key: DefaultsKey<T?>) {
        store.remove(key)
    }

    /// Removes all the user defaults items.
    override fun clear() {
        store.clear()
    }
}