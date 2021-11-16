package org.abuhuraira.app.common.app

import android.app.Activity
import com.example.coreandroid.sources.controls.ApplicationService
import com.example.coreandroid.sources.data.DataWorkerType
import com.example.coreandroid.sources.dependencies.HasDependencies
import com.example.coreandroid.sources.logging.LogHelper
import com.example.coreandroid.sources.protocols.AuthenticationServiceDelegate

/**
 * Created by ahmedsaad on 2018-02-06.
 * Copyright © 2017. All rights reserved.
 */
class DataApplicationService: ApplicationService, HasDependencies, AuthenticationServiceDelegate {

    private val dataWorker: DataWorkerType by lazy {
        dependencies.resolveDataWorker()
    }

    override fun onCreate() {
        dataWorker.configure()
    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun authenticationDidLogin(userID: Int) {}

    override fun authenticationDidLogout(userID: Int) {
        // Reconfigure and sync data for anonymous
        //dataWorker.remotePull {
            // Delete unused user's database for secure clean up
            if (userID > 0) {
                try { this.dataWorker.delete(userID) } catch (e: Exception) {
                    LogHelper.e(messages = *arrayOf("An error occurred while trying to delete user " +
                            "from realm: ${e.localizedMessage}."))
                }
            }
       // }
    }
}