package org.abuhuraira.app.common.app

import android.app.Application
import com.example.ahcstores.sources.dependencies.HasDependencies
import com.example.coreandroid.sources.CoreType
import com.example.coreandroid.sources.controls.ApplicationService
import org.abuhuraira.app.common.AppDependency

/**
 * Created by ahmedsaad on 2018-02-06.
 * Copyright © 2017. All rights reserved.
 */
class CoreApplicationService(private val application: Application): ApplicationService, HasDependencies {
    private val core: CoreType by lazy {
        dependencies.resolveCore()
    }

    override fun onCreate() {
        core.configure(application, AppDependency())
    }
}