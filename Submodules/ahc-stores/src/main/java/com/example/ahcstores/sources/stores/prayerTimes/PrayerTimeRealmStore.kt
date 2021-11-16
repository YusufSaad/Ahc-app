package com.example.ahcstores.sources.stores.prayerTimes

import com.example.ahcstores.sources.extensions.toDate
import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTime
import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTimeModels
import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTimeRealmObject
import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTimeType
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.errors.DataError
import com.example.coreandroid.sources.extensions.callRealmInBackgroundWithCompletionOnUi
import com.example.coreandroid.sources.extensions.startOfDay

class PrayerTimeRealmStore: PrayerTimesCacheStore {

    override fun fetch(request: PrayerTimeModels.Request, completion: Result<List<PrayerTimeType>, DataError>) {
        callRealmInBackgroundWithCompletionOnUi(completion = completion) { realm, tcs ->
            val list = realm.where(PrayerTimeRealmObject::class.java)
                    .equalTo("date", request.date.toDate().startOfDay())
                    .findAll().mapNotNull { PrayerTime(it) }

            tcs.setResult(list)
        }
    }

    override fun createOrUpdate(request: PrayerTimeType, completion: Result<PrayerTimeType, DataError>) {
        callRealmInBackgroundWithCompletionOnUi(completion = completion) { realm, tcs ->
            // Get refreshed object to return
            var item = realm.where(PrayerTimeRealmObject::class.java)
                    .equalTo("nameRaw", request.name.displayName)
                    .and()
                    .equalTo("date", request.date.startOfDay())
                    .findFirst()

            if (item == null) {
                realm.executeTransaction {
                    realm.insert(PrayerTimeRealmObject(request))
                }

                item = realm.where(PrayerTimeRealmObject::class.java)
                        .equalTo("nameRaw", request.name.displayName)
                        .and()
                        .equalTo("date", request.date.startOfDay())
                        .findFirst()
            } else {
                realm.executeTransaction {
                    item.iqama = request.iqama
                    item.athan = request.athan
                }
            }

            if (item == null) {
                tcs.setError(DataError.NonExistent)
            } else {
                tcs.setResult(PrayerTime(item))
            }
        }
    }

    override fun delete(request: PrayerTimeType, completion: Result<Void, DataError>) {
        callRealmInBackgroundWithCompletionOnUi(completion = completion) { realm, tcs ->
            // Get refreshed object to return
            val item = realm.where(PrayerTimeRealmObject::class.java)
                    .equalTo("nameRaw", request.name.displayName)
                    .and()
                    .equalTo("date", request.date.startOfDay())
                    .findFirst()

            if (item != null) {
                realm.executeTransaction {
                    item.deleteFromRealm()
                }
            }

        }
    }

}