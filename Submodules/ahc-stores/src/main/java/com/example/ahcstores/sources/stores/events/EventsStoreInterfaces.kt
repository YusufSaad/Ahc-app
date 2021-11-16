package com.example.ahcstores.sources.stores.events

import com.example.ahcstores.sources.stores.events.models.EventType
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.errors.DataError

interface EventsStore {
    fun fetch(completion: Result<List<EventType>, DataError>)
}

interface EventsCacheStore: EventsStore {
    fun createOrUpdate(request: EventType, completion: Result<EventType, DataError>)
}

interface EventsWorkerType: EventsStore