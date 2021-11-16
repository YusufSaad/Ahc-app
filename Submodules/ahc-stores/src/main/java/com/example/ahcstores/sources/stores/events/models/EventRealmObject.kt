package com.example.ahcstores.sources.stores.events.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class EventRealmObject(
        override var name: String = "",
        override var description: String? = null,
        override var detailURL: String? = null,
        @PrimaryKey
        override var imageURL: String? = null): EventType, RealmObject() {


    constructor(from: EventType?): this() {
        from?.let { event ->
            this.name = event.name
            this.description = event.description
            this.detailURL = event.detailURL
            this.imageURL = event.imageURL
        }
    }
}