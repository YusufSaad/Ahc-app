package org.abuhuraira.app.common.app

import com.crashlytics.android.Crashlytics
import com.example.coreandroid.sources.controls.ApplicationService
import com.example.coreandroid.sources.dependencies.HasDependencies
import io.fabric.sdk.android.Fabric

/**
 * Created by ahmedsaad on 2018-02-06.
 * Copyright © 2017. All rights reserved.
 */
class ErrorApplicationService: ApplicationService, HasDependencies {

    override fun onCreate() {
        Fabric.with(dependencies.resolveContext(), Crashlytics())
    }
}