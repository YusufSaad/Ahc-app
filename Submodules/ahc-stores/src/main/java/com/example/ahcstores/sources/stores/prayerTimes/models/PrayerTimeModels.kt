package com.example.ahcstores.sources.stores.prayerTimes.models

import com.batoulapps.adhan.CalculationParameters
import com.batoulapps.adhan.Coordinates
import com.batoulapps.adhan.data.DateComponents
import com.example.coreandroid.sources.extensions.startOfDay
import java.util.*

/**
 * Created by ahmedsaad on 2018-03-09.
 * Copyright © 2018. All rights reserved.
 */
sealed class PrayerTimeModels {
    class Request(
            val date: DateComponents = DateComponents.from(Date().startOfDay()), // Default to today
            val coordinates: Coordinates = Coordinates(43.7727247, -79.3344978), // Default to AHC location
            val params: CalculationParameters = CalculationParameters(15.0, 12.0) // Default to AHC 12 for isha
    ): PrayerTimeModels()
}