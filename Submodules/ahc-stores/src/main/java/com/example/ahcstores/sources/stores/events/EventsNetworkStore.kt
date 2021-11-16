package com.example.ahcstores.sources.stores.events

import com.example.ahcstores.sources.network.APIRouter.*
import com.example.ahcstores.sources.stores.events.models.Event
import com.example.ahcstores.sources.stores.events.models.EventType
import com.example.coreandroid.sources.common.CompletionResponse.Companion.failure
import com.example.coreandroid.sources.common.CompletionResponse.Companion.success
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.common.initDataError
import com.example.coreandroid.sources.errors.DataError
import com.example.coreandroid.sources.logging.LogHelper
import com.example.coreandroid.sources.network.APISessionType
import org.jsoup.Jsoup

/**
 * Created by ahmedsaad on 2018-02-22.
 * Copyright © 2018. All rights reserved.
 */
class EventsNetworkStore(val apiSession: APISessionType): EventsStore {

    override fun fetch(completion: Result<List<EventType>, DataError>) {
        apiSession.request(ReadEvents()) {
            if (it.value == null || !it.isSuccess) {
                val exception = initDataError(it.error)
                completion(failure(exception))
                LogHelper.e(messages = *arrayOf("An error occurred while fetching events: " +
                        exception.localizedMessage))
                return@request
            }

            try {
                val doc = Jsoup.parse(it.value?.data)
                val elements = doc.getElementsByClass("ult-carousel-wrapper")
                        .firstOrNull()?.getElementsByClass("wpb_wrapper")

                val charities = elements?.fold(arrayListOf<EventType>()) {acc, element ->
                    val name = element.getElementsByTag("h5").firstOrNull()?.text()
                    val detailURL = element.getElementsByClass("vc_general")
                            ?.firstOrNull()?.attr("href")
                    val imageURL = element.getElementsByTag("img")?.firstOrNull()
                            ?.attr("src")


                    if (imageURL != null && name != null) {
                        acc.add(
                                Event(
                                        name = name,
                                        detailURL = detailURL,
                                        imageURL = imageURL
                                )
                        )
                    }

                    acc
                }

                completion(success(charities))
            } catch (e: Exception) {
                completion(failure(DataError.ParseFailure(e)))
                LogHelper.e(messages = *arrayOf("An error occurred while parsing charities: " +
                        e.localizedMessage))
                return@request
            }
        }
    }

}