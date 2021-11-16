package com.example.ahcstores.sources.stores.prayerTimes

import com.example.ahcstores.sources.enums.PrayerName
import com.example.ahcstores.sources.network.APIRouter.*
import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTime
import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTimeModels
import com.example.ahcstores.sources.stores.prayerTimes.models.PrayerTimeType
import com.example.coreandroid.sources.common.CompletionResponse.Companion.failure
import com.example.coreandroid.sources.common.CompletionResponse.Companion.success
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.common.initDataError
import com.example.coreandroid.sources.errors.DataError
import com.example.coreandroid.sources.extensions.startOfDay
import com.example.coreandroid.sources.logging.LogHelper
import com.example.coreandroid.sources.network.APISessionType
import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ahmedsaad on 2018-02-22.
 * Copyright © 2018. All rights reserved.
 */
class PrayerTimesNetworkStore(val apiSession: APISessionType): PrayerTimesStore {

    private val prayerTimeFormatter = SimpleDateFormat("hh:mm a", Locale.US)

    override fun fetch(request: PrayerTimeModels.Request, completion: Result<List<PrayerTimeType>, DataError>) {
        apiSession.request(ReadPrayerTimes()) {
            if (it.value == null || !it.isSuccess) {
                val exception = initDataError(it.error)
                completion(failure(exception))
                LogHelper.e(messages = *arrayOf("An error occurred while fetching prayer times: " +
                        exception.localizedMessage))
                return@request
            }

            try {
                val doc = Jsoup.parse(it.value?.data)
                val elements = doc.getElementsByClass("kf_masjid_prayer_time")

                val prayerTimes = elements.fold(arrayListOf<PrayerTimeType>()) {acc, element ->
                    val name = element.getElementsByTag("h3")?.firstOrNull()?.text()
                    val time = element.getElementsByTag("h4")?.firstOrNull()?.text()


                    val iqama = try {
                        prayerTimeFormatter.parse(time)
                    } catch (e: Exception) {
                        Date(0)
                    }

                    acc.add(
                            PrayerTime(
                                    name = PrayerName.valueOf(name ?: ""),
                                    iqama = iqama,
                                    date = Date().startOfDay()
                            )
                    )

                    acc
                }

                completion(success(prayerTimes))
            } catch (e: Exception) {
                completion(failure(DataError.ParseFailure(e)))
                LogHelper.e(messages = *arrayOf("An error occurred while parsing prayer times: " +
                        e.localizedMessage))
                return@request
            }
        }
    }

}