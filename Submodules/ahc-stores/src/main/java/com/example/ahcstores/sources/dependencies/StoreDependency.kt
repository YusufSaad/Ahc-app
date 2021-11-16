package com.example.ahcstores.sources.dependencies

import com.example.ahcstores.sources.AppStore
import com.example.ahcstores.sources.stores.charities.*
import com.example.ahcstores.sources.stores.events.*
import com.example.ahcstores.sources.stores.prayerTimes.*
import com.example.ahcstores.sources.stores.videos.VideosNetworkStore
import com.example.ahcstores.sources.stores.videos.VideosStore
import com.example.ahcstores.sources.stores.videos.VideosWorker
import com.example.ahcstores.sources.stores.videos.VideosWorkerType
import com.example.coreandroid.sources.CoreType
import com.example.coreandroid.sources.dependencies.CoreDependency

open class StoreDependency: CoreDependency(), Dependency {
    override fun resolveCore(): CoreType {
        return AppStore()
    }

    override fun resolvePrayerTimesWorker(): PrayerTimesWorkerType {
        return PrayerTimesWorker(
                calculationStore = resolvePrayerTimesCalculationStore(),
                networkStore = resolvePrayerTimesNetworkStore(),
                cacheStore = resolvePrayerTimesCacheStore()
        )
    }

    override fun resolveCharitiesWorker(): CharitiesWorkerType {
        return CharitiesWorker(
                store = resolveCharitiesNetworkStore(),
                cacheStore = resolveCharitiesCacheStore()
        )
    }

    override fun resolveVideosWorker(): VideosWorkerType {
        return VideosWorker(
                store = resolveVideosNetworkStore()
        )
    }

    override fun resolveEventsWorker(): EventsWorkerType {
        return EventsWorker(
                store = resolveEventsNetworkStore(),
                cacheStore = resolveEventsCacheStore()
        )
    }

    override fun resolvePrayerTimesCalculationStore(): PrayerTimesStore {
        return PrayerTimesCalculationStore()
    }

    override fun resolvePrayerTimesNetworkStore(): PrayerTimesStore {
        return PrayerTimesNetworkStore(apiSession = resolveAPISessionService())
    }

    override fun resolveCharitiesNetworkStore(): CharitiesStore {
        return CharitiesNetworkStore(apiSession = resolveAPISessionService())
    }

    override fun resolveEventsNetworkStore(): EventsStore {
        return EventsNetworkStore(apiSession = resolveAPISessionService())
    }

    override fun resolveVideosNetworkStore(): VideosStore {
        return VideosNetworkStore(
                httpService = resolveHTTPService(),
                preferencesWorker = resolvePreferencesWorker(),
                constants = resolveConstants()
        )
    }

    override fun resolvePrayerTimesCacheStore(): PrayerTimesCacheStore {
        return PrayerTimeRealmStore()
    }

    override fun resolveCharitiesCacheStore(): CharitiesCacheStore {
        return CharitiesRealmStore()
    }

    override fun resolveEventsCacheStore(): EventsCacheStore {
        return EventsRealmStore()
    }
}