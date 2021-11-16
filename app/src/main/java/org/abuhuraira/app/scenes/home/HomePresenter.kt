package org.abuhuraira.app.scenes.home

import com.example.ahcstores.sources.enums.PrayerName
import com.example.coreandroid.sources.errors.DataError
import com.example.coreandroid.sources.extensions.add
import org.abuhuraira.app.R
import org.abuhuraira.app.common.cleanBase.AppModels
import org.abuhuraira.app.common.extensions.getString
import org.abuhuraira.app.scenes.home.common.HomeDisplayable
import org.abuhuraira.app.scenes.home.common.HomeModels
import org.abuhuraira.app.scenes.home.common.HomePresentable
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ahmedsaad on 2017-10-27.
 * Copyright © 2017. All rights reserved.
 */

class HomePresenter(private val fragment: WeakReference<HomeDisplayable?>) : HomePresentable {
    private val dateFormatter = SimpleDateFormat("MMMM d yyyy", Locale.US)
    private val prayerTimeFormatter = SimpleDateFormat("hh:mm a", Locale.US)


    override fun presentFetchedPrayerTimes(response: HomeModels.PrayerTimesResponse) {
        val viewModel = HomeModels.PrayerTimesViewModel(
                currentDate = dateFormatter.format(Date()),
                prayerTimes = response.prayerTimes.fold(arrayListOf()) { acc, prayerTimeType ->
                    val prayerTime = HomeModels.PrayerTimeViewModel(
                            name = prayerTimeType.name.displayName,
                            athan = if (prayerTimeType.athan == Date(0))
                                "" else prayerTimeFormatter.format(prayerTimeType.athan),
                            iqama = if (prayerTimeType.iqama == Date(0)) {
                                when {
                                    prayerTimeType.name == PrayerName.Fajar -> prayerTimeFormatter
                                            .format(prayerTimeType.athan.add(Calendar.MINUTE, 20))
                                    prayerTimeType.name == PrayerName.Maghrib -> prayerTimeFormatter
                                            .format(prayerTimeType.athan.add(Calendar.MINUTE, 5))
                                    else -> ""
                                }
                            } else prayerTimeFormatter.format(prayerTimeType.iqama)
                    )
                    acc.add(prayerTime)
                    acc
                }
        )

        fragment.get()?.displayFetchedPrayerTimes(viewModel)
    }

    override fun presentFetchedPrayerTimes(error: DataError) {
        // Handle and parse error
        val viewModel = AppModels.Error(
                title = getString(R.string.generic_error_title),
                message = getString(R.string.generic_error_message)
        )

        fragment.get()?.displaySupport(viewModel)
    }

    override fun presentFetchedEvents(response: HomeModels.EventsResponse) {
        val viewModel = HomeModels.EventsViewModel(
                events = response.events.fold(arrayListOf()) { acc, eventType ->
                    val event = HomeModels.EventViewModel(
                            name = eventType.name,
                            imageURL = eventType.imageURL,
                            detailURL = eventType.detailURL
                    )
                    acc.add(event)
                    acc
                }
        )

        fragment.get()?.displayFetchedEvents(viewModel)
    }

    override fun presentFetchedEvents(error: DataError) {
        // Handle and parse error
        val viewModel = AppModels.Error(
                title = getString(R.string.generic_error_title),
                message = getString(R.string.generic_error_message)
        )

        fragment.get()?.displaySupport(viewModel)
    }

}