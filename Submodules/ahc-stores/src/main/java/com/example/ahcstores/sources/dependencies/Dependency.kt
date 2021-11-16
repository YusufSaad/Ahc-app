package com.example.ahcstores.sources.dependencies

import com.example.ahcstores.sources.stores.charities.CharitiesCacheStore
import com.example.ahcstores.sources.stores.charities.CharitiesStore
import com.example.ahcstores.sources.stores.charities.CharitiesWorkerType
import com.example.ahcstores.sources.stores.events.EventsCacheStore
import com.example.ahcstores.sources.stores.events.EventsStore
import com.example.ahcstores.sources.stores.events.EventsWorkerType
import com.example.ahcstores.sources.stores.prayerTimes.PrayerTimesCacheStore
import com.example.ahcstores.sources.stores.prayerTimes.PrayerTimesStore
import com.example.ahcstores.sources.stores.prayerTimes.PrayerTimesWorkerType
import com.example.ahcstores.sources.stores.videos.VideosStore
import com.example.ahcstores.sources.stores.videos.VideosWorkerType
import com.example.coreandroid.sources.dependencies.Dependency


/**
 * Created by ahmedsaad on 2017-11-29.
 * Copyright © 2017. All rights reserved.
 */
interface Dependency: Dependency {
    fun resolvePrayerTimesWorker(): PrayerTimesWorkerType
    fun resolveCharitiesWorker(): CharitiesWorkerType
    fun resolveVideosWorker(): VideosWorkerType
    fun resolveEventsWorker(): EventsWorkerType

    fun resolvePrayerTimesCalculationStore(): PrayerTimesStore
    fun resolvePrayerTimesNetworkStore(): PrayerTimesStore
    fun resolveCharitiesNetworkStore(): CharitiesStore
    fun resolveVideosNetworkStore(): VideosStore
    fun resolveEventsNetworkStore(): EventsStore

    fun resolvePrayerTimesCacheStore(): PrayerTimesCacheStore
    fun resolveCharitiesCacheStore(): CharitiesCacheStore
    fun resolveEventsCacheStore(): EventsCacheStore
}