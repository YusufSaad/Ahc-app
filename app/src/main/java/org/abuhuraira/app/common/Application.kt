package org.abuhuraira.app.common

import org.abuhuraira.app.common.app.*
import com.example.coreandroid.sources.controls.ApplicationService
import com.example.coreandroid.sources.controls.CoreApplication

/**
 * Created by ahmedsaad on 2018-01-16.
 * Copyright © 2017. All rights reserved.
 */
class Application: CoreApplication() {
    companion object {
        lateinit var instance: Application
    }

    init {
        instance = this
    }

    override var services: ArrayList<ApplicationService> = {
        arrayListOf(
                CoreApplicationService(this),
                LoggerApplicationService(),
                ErrorApplicationService(),
                DataApplicationService(),
                NotificationApplicationService()
        )
    }()
}