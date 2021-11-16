package com.example.ahcstores.sources.network

import com.example.coreandroid.sources.enums.NetworkMethod
import com.example.coreandroid.sources.network.APIRouter

/**
 * Created by ahmedsaad on 2017-11-03.
 * Copyright © 2017. All rights reserved.
 */

sealed class APIRouter: APIRouter() {
    class ReadPrayerTimes: APIRouter() {
        override val method = NetworkMethod.GET
        override val path = ""
    }

    class ReadEvents: APIRouter() {
        override val method = NetworkMethod.GET
        override val path = ""
    }

    class ReadCharities: APIRouter() {
        override val method = NetworkMethod.GET
        override val path = "donate"
    }
}