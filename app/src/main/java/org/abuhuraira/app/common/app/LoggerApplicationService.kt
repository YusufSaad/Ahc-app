package org.abuhuraira.app.common.app

import org.abuhuraira.app.BuildConfig
import com.example.coreandroid.sources.controls.ApplicationService
import com.example.coreandroid.sources.logging.Logger

/**
 * Created by ahmedsaad on 2018-04-03.
 * Copyright © 2018. All rights reserved.
 */
class LoggerApplicationService: ApplicationService {
    override fun onCreate() {
        Logger.setupLogger(appVersion = BuildConfig.VERSION_NAME, appBuild = BuildConfig.VERSION_CODE)
    }
}