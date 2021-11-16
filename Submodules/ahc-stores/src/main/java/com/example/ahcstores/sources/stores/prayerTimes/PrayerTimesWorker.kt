package com.example.ahcstores.sources.stores.prayerTimes

import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTimeModels
import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTimeType
import com.example.coreandroid.sources.common.CompletionResponse
import com.example.coreandroid.sources.common.DispatchGroup
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.errors.DataError
import com.example.coreandroid.sources.logging.LogHelper
import java.util.*


/**
* Created by ahmedsaad on 2017-10-24.
* Copyright © 2017. All rights reserved.
*/

class PrayerTimesWorker (val calculationStore: PrayerTimesStore,
                         val networkStore: PrayerTimesStore,
                         val cacheStore: PrayerTimesCacheStore?) : PrayerTimesWorkerType {

    override fun fetchCalculation(request: PrayerTimeModels.Request, completion: Result<List<PrayerTimeType>, DataError>) {
        calculationStore.fetch(request, completion = completion)
    }

    override fun fetchIqama(completion: Result<ArrayList<PrayerTimeType>, DataError>) {
        if (cacheStore == null) {
            networkStore.fetch {
                completion(CompletionResponse(it.isSuccess, ArrayList(it.value), it.error))
            }
            return
        }

        cacheStore.fetch {
            // Immediately return local response
            completion(CompletionResponse(it.isSuccess, ArrayList(it.value), it.error))

            if (!it.isSuccess) { return@fetch }

            val cachePrayerTimes = it.value
            // Sync remote updates to cache if applicable
            this.networkStore.fetch {
                // Validate if any updates that needs to be stored
                if (it.value == null || !it.isSuccess) {
                    return@fetch
                }

                val taskGroup = DispatchGroup()
                var hasTasks = false

                // Update local storage with updated data
                it.value
                        ?.filter {prayerTime ->
                            val cachePrayerTime = cachePrayerTimes?.firstOrNull { it.name == prayerTime.name }

                            if (cachePrayerTime == null) {
                                true
                            } else {
                                cachePrayerTime.iqama != prayerTime.iqama
                            }

                        }
                        ?.forEach {
                            taskGroup.enter()

                            if (it.iqama == Date(0)) {
                                this.cacheStore.delete(it) {
                                    if (!it.isSuccess) {
                                        LogHelper.e(messages = *arrayOf("Could not delete " +
                                                "prayer time locally from remote storage: ${it.error?.localizedMessage
                                                        ?: ""}"))
                                        taskGroup.leave()
                                        return@delete
                                    }

                                    hasTasks = true
                                    taskGroup.leave()
                                }
                            } else {
                                this.cacheStore.createOrUpdate(it) {
                                    if (!it.isSuccess) {
                                        LogHelper.e(messages = *arrayOf("Could not save new or updated " +
                                                "prayer time locally from remote storage: ${it.error?.localizedMessage
                                                        ?: ""}"))
                                        taskGroup.leave()
                                        return@createOrUpdate
                                    }

                                    hasTasks = true
                                    taskGroup.leave()
                                }
                            }
                        }

                taskGroup.notify(Runnable {
                    // Callback handler again if updated
                    if (!hasTasks) { return@Runnable }

                    this.cacheStore.fetch {
                        completion(CompletionResponse(it.isSuccess, ArrayList(it.value), it.error))
                    }
                })

            }
        }
    }
}