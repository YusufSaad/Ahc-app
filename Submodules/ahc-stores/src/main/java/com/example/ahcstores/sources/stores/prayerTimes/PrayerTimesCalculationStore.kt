package com.example.ahcstores.sources.stores.prayerTimes

import com.batoulapps.adhan.PrayerTimes
import com.batoulapps.adhan.data.CalendarUtil
import com.example.ahcstores.sources.enums.PrayerName
import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTime
import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTimeModels
import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTimeType
import com.example.coreandroid.sources.common.CompletionResponse.Companion.failure
import com.example.coreandroid.sources.common.CompletionResponse.Companion.success
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.errors.DataError
import com.example.coreandroid.sources.extensions.startOfDay

/**
 * Created by ahmedsaad on 2018-02-22.
 * Copyright © 2018. All rights reserved.
 */
class PrayerTimesCalculationStore: PrayerTimesStore {

    override fun fetch(request: PrayerTimeModels.Request, completion: Result<List<PrayerTimeType>, DataError>) {
        try {
            val prayerTimes = PrayerTimes(request.coordinates, request.date, request.params)
            val date = CalendarUtil.resolveTime(request.date).startOfDay()

            val listOfPrayerTimes = listOf(
                    PrayerTime(PrayerName.Fajar, athan = prayerTimes.fajr, date = date),
                    PrayerTime(PrayerName.Sunrise, athan = prayerTimes.sunrise, date = date),
                    PrayerTime(PrayerName.Dhaur, athan = prayerTimes.dhuhr, date = date),
                    PrayerTime(PrayerName.Asar, athan = prayerTimes.asr, date = date),
                    PrayerTime(PrayerName.Maghrib, athan = prayerTimes.maghrib, date = date),
                    PrayerTime(PrayerName.Isha, athan = prayerTimes.isha, date = date)
            )

            completion(success(listOfPrayerTimes))
        } catch (e: Exception) {
            completion(failure(DataError.UnknownReason(e)))
        }
    }

}