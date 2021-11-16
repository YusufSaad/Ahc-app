package org.abuhuraira.app.scenes.home.common

import com.example.coreandroid.sources.errors.DataError
import org.abuhuraira.app.common.cleanBase.AppDisplayable
import org.abuhuraira.app.common.routers.AppRoutable

/**
* Created by ahmedsaad on 2017-10-24.
* Copyright © 2017. All rights reserved.
*/

interface HomeDisplayable: AppDisplayable {
    fun displayFetchedPrayerTimes(viewModel: HomeModels.PrayerTimesViewModel)
    fun displayFetchedEvents(viewModel: HomeModels.EventsViewModel)
}

interface HomeBusinessLogic {
    fun fetchPrayerTimes(request: HomeModels.PrayerTimesRequest)
    fun fetchEvents(request: HomeModels.EventsRequest)
}

interface HomePresentable {
    fun presentFetchedPrayerTimes(response: HomeModels.PrayerTimesResponse)
    fun presentFetchedPrayerTimes(error: DataError)

    fun presentFetchedEvents(response: HomeModels.EventsResponse)
    fun presentFetchedEvents(error: DataError)
}

interface HomeRoutable: AppRoutable {
}