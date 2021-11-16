package com.example.ahcstores.sources.stores.charities

import com.example.ahcstores.sources.network.APIRouter.*
import com.example.ahcstores.sources.stores.charities.models.Charity
import com.example.ahcstores.sources.stores.charities.models.CharityType
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
class CharitiesNetworkStore(val apiSession: APISessionType): CharitiesStore {

    override fun fetch(completion: Result<List<CharityType>, DataError>) {
        apiSession.request(ReadCharities()) {
            if (it.value == null || !it.isSuccess) {
                val exception = initDataError(it.error)
                completion(failure(exception))
                LogHelper.e(messages = *arrayOf("An error occurred while fetching charities: " +
                        exception.localizedMessage))
                return@request
            }

            try {
                val doc = Jsoup.parse(it.value?.data)
                val elements = doc.getElementsByClass("Donate-item-txt")

                val charities = elements.fold(arrayListOf<CharityType>()) {acc, element ->
                    val name = element.text()
                    val donationURL = element.nextElementSibling().getElementsByTag("a")
                            ?.firstOrNull()?.attr("href")
                    val imageURL = element.nextElementSibling().getElementsByTag("img")
                            ?.firstOrNull()?.attr("src")


                    if (donationURL != null) {
                        acc.add(
                                Charity(
                                        name = name,
                                        donationURL = donationURL,
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