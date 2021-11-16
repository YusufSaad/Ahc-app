package com.example.ahcstores.sources.stores.prayerTimes.models

import com.example.ahcstores.sources.enums.PrayerName
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.Index
import java.util.*

open class PrayerTimeRealmObject(
        @Index
        var nameRaw: String = "",
        override var athan: Date = Date(0),
        override var iqama: Date = Date(0),
        @Index
        override var date: Date = Date(0)): PrayerTimeType, RealmObject() {

    @Ignore
    override var name: PrayerName = PrayerName.Unknown
        get() = PrayerName.valueOf(nameRaw)
        set(value) {
            nameRaw = value.displayName
            field = value
        }

    constructor(from: PrayerTimeType?): this() {
        from?.let { prayerTime ->
            this.name = prayerTime.name
            this.athan = prayerTime.athan
            this.iqama = prayerTime.iqama
            this.date = prayerTime.date
        }
    }
}