package com.example.coreandroid.sources

import android.app.Application
import com.example.coreandroid.sources.dependencies.Dependency
import com.example.coreandroid.sources.dependencies.DependencyInjector
import io.realm.Realm

/**
 * Created by ahmedsaad on 2018-01-03.
 * Copyright © 2017. All rights reserved.
 */

interface CoreType {
    fun configure(dependencies: Dependency)
    fun configure(application: Application, dependencies: Dependency)
}

open class AppCore: CoreType {
    override fun configure(dependencies: Dependency) {
        // Declare dependencies used throughout app
        DependencyInjector.dependencies = dependencies

        // Configure Realm
        val context = dependencies.resolveContext()
        if (context != null) {
            Realm.init(context)
        }
    }

    override fun configure(application: Application, dependencies: Dependency) {
        dependencies.application = application
        configure(dependencies =  dependencies)
    }
}