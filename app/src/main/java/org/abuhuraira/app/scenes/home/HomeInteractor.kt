package org.abuhuraira.app.scenes.home

import com.example.ahcstores.sources.stores.events.EventsWorkerType
import com.example.ahcstores.sources.stores.prayerTimes.PrayerTimesWorkerType
import com.example.coreandroid.sources.errors.DataError
import org.abuhuraira.app.scenes.home.common.HomeBusinessLogic
import org.abuhuraira.app.scenes.home.common.HomeModels
import org.abuhuraira.app.scenes.home.common.HomePresentable
import java.util.*

/**
 * Created by ahmedsaad on 2017-10-27.
 * Copyright © 2017. All rights reserved.
 */

class HomeInteractor(val presenter: HomePresentable,
                     val prayerTimesWorker: PrayerTimesWorkerType,
                     val eventsWorker: EventsWorkerType) : HomeBusinessLogic {

    override fun fetchPrayerTimes(request: HomeModels.PrayerTimesRequest) {
        prayerTimesWorker.fetchCalculation {
            val prayerTimes = it.value
            if (prayerTimes == null || !it.isSuccess) {
                this.presenter.presentFetchedPrayerTimes(error = it.error
                        ?: DataError.UnknownReason(null))
                return@fetchCalculation
            }

            this.presenter.presentFetchedPrayerTimes(
                    HomeModels.PrayerTimesResponse(
                            prayerTimes = prayerTimes
                    )
            )

            prayerTimesWorker.fetchIqama {
                val iqamaTimes = it.value
                if (iqamaTimes == null || !it.isSuccess) {
                    // Fail silently
                    return@fetchIqama
                }

                // Merge Iqama and prayer times
                iqamaTimes.forEach {iqama ->
                    val athan = prayerTimes.firstOrNull {it.name == iqama.name}?.athan ?: Date(0)
                    iqama.athan = athan
                }

                prayerTimes.forEach { prayer ->
                    if (!iqamaTimes.any { it.name == prayer.name }) { iqamaTimes.add(prayer) }
                }

                this.presenter.presentFetchedPrayerTimes(
                        HomeModels.PrayerTimesResponse(
                                prayerTimes = iqamaTimes.sortedBy { it.name.ordinal }
                        )
                )
            }
        }
    }

    override fun fetchEvents(request: HomeModels.EventsRequest) {
        eventsWorker.fetch {
            val events = it.value
            if (events == null || !it.isSuccess) {
                this.presenter.presentFetchedEvents(error = it.error
                        ?: DataError.UnknownReason(null))
                return@fetch
            }

            this.presenter.presentFetchedEvents(
                    HomeModels.EventsResponse(
                            events = events
                    )
            )
        }
    }
}