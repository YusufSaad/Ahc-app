package com.example.coreandroid.sources.data

import com.example.coreandroid.sources.enums.DefaultsKeys.Companion.userID
import com.example.coreandroid.sources.logging.LogHelper
import com.example.coreandroid.sources.preferences.PreferencesWorkerType
import io.realm.Realm
import io.realm.RealmConfiguration


/**
 * Created by ahmedsaad on 2017-12-01.
 * Copyright © 2017. All rights reserved.
 */
open class DataRealmStore(override val preferencesWorker: PreferencesWorkerType): DataStore {

    init {
        this.configure()
    }

    override fun configure() {
        val realmFileName = "$name.realm"

        // Skip if already set up before
        if (Realm.getDefaultConfiguration()?.realmFileName != realmFileName) {
            val config = RealmConfiguration.Builder()
                    .name(realmFileName)
                    .deleteRealmIfMigrationNeeded()
                    .compactOnLaunch { totalBytes, usedBytes ->
                        // Compact if the file is over X MB in size and less than 50% 'used'
                        // https://realm.io/docs/swift/latest/#compacting-realms
                        val maxSize = 100 * 1024 * 1024 //100MB
                        val shouldCompact = (totalBytes > maxSize) && (Double.fromBits(usedBytes) / Double.fromBits(totalBytes)) < 0.5

                        if (shouldCompact) {
                            // Log should compact
                            LogHelper.w(messages = *arrayOf("Compacting Realm database."))
                        }

                        shouldCompact
                    }
                    .build()

            // Set the configuration used for user's Realm
            Realm.setDefaultConfiguration(config)

            // Attempt to initialize and clean if necessary
            try {
                val realm = Realm.getDefaultInstance()
                realm.close()
            } catch (exception: Exception) {
                LogHelper.e(messages = *arrayOf("Could not initialize Realm database: " +
                        "${exception.localizedMessage}. Deleting database and recreating..."))

                delete(preferencesWorker.get(userID) ?: 0)
            }

            LogHelper.i(messages = *arrayOf("Realm database configured for $name"))
        }
    }

    override fun delete(userID: Int) {
        val realmFileName = "${generateName(userID)}.realm"
        val realmConfiguration: RealmConfiguration?  = RealmConfiguration.Builder()
                .name(realmFileName)
                .build()

        if (realmConfiguration != null)
            Realm.deleteRealm(realmConfiguration)

    }

}