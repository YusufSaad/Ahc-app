package com.example.coreandroid.sources.dependencies

import android.app.Application
import android.content.Context
import com.example.coreandroid.sources.*
import com.example.coreandroid.sources.controls.GooglePlacesSearchService
import com.example.coreandroid.sources.network.APISession
import com.example.coreandroid.sources.network.APISessionType
import com.example.coreandroid.sources.network.HTTPService
import com.example.coreandroid.sources.network.HTTPServiceType
import com.example.coreandroid.sources.data.*
import com.example.coreandroid.sources.preferences.PreferencesResourceStore
import com.example.coreandroid.sources.preferences.PreferencesStore
import com.example.coreandroid.sources.preferences.PreferencesWorker
import com.example.coreandroid.sources.preferences.PreferencesWorkerType
import com.example.coreandroid.sources.security.SecurityPreferenceStore
import com.example.coreandroid.sources.security.SecurityStore
import com.example.coreandroid.sources.security.SecurityWorker
import com.example.coreandroid.sources.security.SecurityWorkerType

/**
 * Created by ahmedsaad on 2017-11-30.
 * Copyright © 2017. All rights reserved.
 */
open class CoreDependency: Dependency {

    override var application: Application? = null

    override fun resolveContext(): Context? {
        return application?.applicationContext
    }

    override fun resolveCore(): CoreType {
        return AppCore()
    }

    override fun resolveConstants(): ConstantsType {
        return AppConstants()
    }

    // Workers

    override fun resolvePreferencesWorker(): PreferencesWorkerType {
        return PreferencesWorker(store = resolvePreferencesStore())
    }

    override fun resolveSecurityWorker(): SecurityWorkerType {
        return SecurityWorker(
                context = resolveContext(),
                store = resolveSecurityStore(),
                constants = resolveConstants())
    }

    override fun resolveDataWorker(): DataWorkerType {
        return DataWorker(store = resolveDataStore())
    }

    // Stores

    override fun resolveDataStore(): DataStore {
        return DataRealmStore(
                preferencesWorker = resolvePreferencesWorker()
        )
    }

    override fun resolvePreferencesStore(): PreferencesStore {
        return PreferencesResourceStore(context = resolveContext())
    }

    override fun resolveSecurityStore(): SecurityStore {
        return SecurityPreferenceStore(context = resolveContext())
    }

    // Services

    override fun resolveHTTPService(): HTTPServiceType {
        return HTTPService()
    }

    override fun resolveAPISessionService(): APISessionType {
        return APISession(
                context = resolveContext(),
                preferencesWorker = resolvePreferencesWorker(),
                securityWorker = resolveSecurityWorker()
        )
    }

    override fun resolvePlacesSearchService(): GooglePlacesSearchService {
        return GooglePlacesSearchService(
                constants = resolveConstants()
        )
    }
}