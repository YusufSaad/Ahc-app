package com.example.ahcstores.sources

import android.app.Application
import com.example.ahcstores.sources.dependencies.Dependency
import com.example.ahcstores.sources.dependencies.DependencyInjector
import com.example.ahcstores.sources.dependencies.StoreDependency
import com.example.coreandroid.sources.AppCore


/**
 * Created by ahmedsaad on 2018-01-03.
 * Copyright © 2017. All rights reserved.
 */
interface CoreType {
    fun configure(dependencies: Dependency)
}

class AppStore: AppCore(), CoreType {
    override fun configure(dependencies: Dependency) {
        // Declare dependencies used throughout app
        DependencyInjector.dependencies = dependencies

        return super.configure(dependencies)
    }

    override fun configure(application: Application, dependencies: com.example.coreandroid.sources.dependencies.Dependency) {
        super.configure(application, dependencies)

        dependencies.application = application
        configure(dependencies as StoreDependency)
    }
}