package com.example.coreandroid.sources.dependencies

import android.app.Application
import android.content.Context
import com.example.coreandroid.sources.ConstantsType
import com.example.coreandroid.sources.CoreType
import com.example.coreandroid.sources.controls.GooglePlacesSearchService
import com.example.coreandroid.sources.network.APISessionType
import com.example.coreandroid.sources.network.HTTPServiceType
import com.example.coreandroid.sources.data.DataStore
import com.example.coreandroid.sources.data.DataWorkerType
import com.example.coreandroid.sources.preferences.PreferencesStore
import com.example.coreandroid.sources.preferences.PreferencesWorkerType
import com.example.coreandroid.sources.security.SecurityStore
import com.example.coreandroid.sources.security.SecurityWorkerType

/**
 * Created by ahmedsaad on 2017-11-29.
 * Copyright © 2017. All rights reserved.
 */
interface Dependency {
    var application: Application?

    fun resolveContext(): Context?

    fun resolveCore(): CoreType
    fun resolveConstants(): ConstantsType

    fun resolveDataWorker(): DataWorkerType
    fun resolvePreferencesWorker(): PreferencesWorkerType
    fun resolveSecurityWorker(): SecurityWorkerType

    fun resolvePreferencesStore(): PreferencesStore
    fun resolveSecurityStore(): SecurityStore
    fun resolveDataStore(): DataStore

    fun resolveHTTPService(): HTTPServiceType
    fun resolveAPISessionService(): APISessionType
    fun resolvePlacesSearchService(): GooglePlacesSearchService
}