package com.example.ahcstores.sources.stores.events

import com.example.ahcstores.sources.stores.events.models.Event
import com.example.ahcstores.sources.stores.events.models.EventRealmObject
import com.example.ahcstores.sources.stores.events.models.EventType
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.errors.DataError
import com.example.coreandroid.sources.extensions.callRealmInBackgroundWithCompletionOnUi

class EventsRealmStore: EventsCacheStore {

    override fun fetch(completion: Result<List<EventType>, DataError>) {
        callRealmInBackgroundWithCompletionOnUi(completion = completion) { realm, tcs ->
            val list = realm.where(EventRealmObject::class.java)
                    .findAll().mapNotNull { Event(it) }

            tcs.setResult(list)
        }
    }

    override fun createOrUpdate(request: EventType, completion: Result<EventType, DataError>) {
        callRealmInBackgroundWithCompletionOnUi(completion = completion) { realm, tcs ->
            realm.executeTransaction {
                realm.insertOrUpdate(EventRealmObject(request))
            }

            // Get refreshed object to return
            val item = realm.where(EventRealmObject::class.java)
                    .equalTo("imageURL", request.imageURL)
                    .findFirst()

            if (item == null) {
                tcs.setError(DataError.NonExistent)
            } else {
                tcs.setResult(Event(item))
            }
        }
    }

}