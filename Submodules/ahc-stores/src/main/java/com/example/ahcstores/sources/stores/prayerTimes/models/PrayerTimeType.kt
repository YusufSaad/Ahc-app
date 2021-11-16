package com.example.ahcstores.sources.stores.prayerTimes.models

import com.example.ahcstores.sources.enums.PrayerName
import java.util.*


/**
 * Created by ahmedsaad on 2017-12-08.
 * Copyright © 2017. All rights reserved.
 */
interface PrayerTimeType {
    var name: PrayerName
    var athan: Date
    var iqama: Date
    var date: Date
}