package com.example.ahcstores.sources.stores.prayerTimes.models

import com.example.ahcstores.sources.enums.PrayerName
import java.util.*


/**
* Created by ahmedsaad on 2017-10-24.
* Copyright © 2017. All rights reserved.
*/

data class PrayerTime(
        override var name: PrayerName = PrayerName.Unknown,
        override var athan: Date = Date(0),
        override var iqama: Date = Date(0),
        override var date: Date = Date(0)): PrayerTimeType {


    constructor(from: PrayerTimeType?): this() {
        from?.let { prayerTime ->
            this.name = prayerTime.name
            this.athan = prayerTime.athan
            this.iqama = prayerTime.iqama
            this.date = prayerTime.date
        }
    }
}