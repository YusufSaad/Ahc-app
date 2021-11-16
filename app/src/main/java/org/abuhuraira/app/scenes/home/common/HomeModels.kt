package org.abuhuraira.app.scenes.home.common

import com.example.ahcstores.sources.stores.events.models.EventType
import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTimeType


/**
 * Created by ahmedsaad on 2018-02-20.
 * Copyright © 2017. All rights reserved.
 */
sealed class HomeModels {
    class PrayerTimesRequest: HomeModels()

    class EventsRequest: HomeModels()

    class PrayerTimesResponse(
            val prayerTimes: List<PrayerTimeType>): HomeModels()

    class EventsResponse(
            val events: List<EventType>): HomeModels()

    class PrayerTimesViewModel(
            val currentDate: String,
            val prayerTimes: List<PrayerTimeViewModel>): HomeModels()

    class PrayerTimeViewModel(
            val name: String,
            val athan: String,
            val iqama: String
    ): HomeModels()

    class EventsViewModel(
            val events: List<EventViewModel>): HomeModels()

    class EventViewModel(
            val name: String,
            val imageURL: String?,
            val detailURL: String?): HomeModels()
}