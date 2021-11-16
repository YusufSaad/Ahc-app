package com.example.coreandroid.sources.data

/**
 * Created by ahmedsaad on 2017-12-01.
 * Copyright © 2017. All rights reserved.
 */
class DataWorker(private val store: DataStore): DataWorkerType {

    override fun delete(userID: Int) {
        store.delete(userID)
    }

    override fun configure() = store.configure()
}