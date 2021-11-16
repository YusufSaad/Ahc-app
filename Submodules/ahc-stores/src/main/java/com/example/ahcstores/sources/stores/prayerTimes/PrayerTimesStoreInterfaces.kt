package com.example.ahcstores.sources.stores.prayerTimes

import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTimeModels
import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTimeType
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.errors.DataError


/**
 * Created by ahmedsaad on 2017-10-30.
 * Copyright © 2017. All rights reserved.
 */

interface PrayerTimesStore {
    fun fetch(request: PrayerTimeModels.Request = PrayerTimeModels.Request(),
              completion: Result<List<PrayerTimeType>, DataError>)
}

interface PrayerTimesCacheStore: PrayerTimesStore {
    fun createOrUpdate(request: PrayerTimeType, completion: Result<PrayerTimeType, DataError>)
    fun delete(request: PrayerTimeType, completion: Result<Void, DataError>)
}

interface PrayerTimesWorkerType {
    fun fetchCalculation(request: PrayerTimeModels.Request = PrayerTimeModels.Request(),
                         completion: Result<List<PrayerTimeType>, DataError>)
    fun fetchIqama(completion: Result<ArrayList<PrayerTimeType>, DataError>)
}