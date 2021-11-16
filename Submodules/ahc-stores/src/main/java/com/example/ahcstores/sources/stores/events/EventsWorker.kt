package com.example.ahcstores.sources.stores.events

import com.example.ahcstores.sources.stores.events.models.EventType
import com.example.coreandroid.sources.common.CompletionResponse
import com.example.coreandroid.sources.common.DispatchGroup
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.errors.DataError
import com.example.coreandroid.sources.logging.LogHelper


/**
* Created by ahmedsaad on 2017-10-24.
* Copyright © 2017. All rights reserved.
*/

class EventsWorker (val store: EventsStore,
                    val cacheStore: EventsCacheStore?) : EventsWorkerType {


    override fun fetch(completion: Result<List<EventType>, DataError>) {
        if (cacheStore == null) {
            store.fetch {
                completion(CompletionResponse(it.isSuccess, ArrayList(it.value), it.error))
            }
            return
        }

        cacheStore.fetch {
            // Immediately return local response
            completion(CompletionResponse(it.isSuccess, ArrayList(it.value), it.error))

            if (!it.isSuccess) {
                return@fetch
            }

            // Sync remote updates to cache if applicable
            this.store.fetch {
                // Validate if any updates that needs to be stored
                if (it.value == null || !it.isSuccess) {
                    return@fetch
                }

                val taskGroup = DispatchGroup()
                var hasTasks = false

                // Update local storage with updated data
                it.value
                        ?.forEach {
                            taskGroup.enter()

                            this.cacheStore.createOrUpdate(it) {
                                if (!it.isSuccess) {
                                    LogHelper.e(messages = *arrayOf("Could not save new or updated " +
                                            "event locally from remote storage: ${it.error?.localizedMessage
                                                    ?: ""}"))
                                    taskGroup.leave()
                                    return@createOrUpdate
                                }

                                hasTasks = true
                                taskGroup.leave()
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