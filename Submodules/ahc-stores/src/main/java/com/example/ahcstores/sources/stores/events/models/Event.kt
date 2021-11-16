package com.example.ahcstores.sources.stores.events.models



/**
* Created by ahmedsaad on 2017-10-24.
* Copyright © 2017. All rights reserved.
*/

data class Event(
        override var name: String = "",
        override var description: String? = null,
        override var detailURL: String? = null,
        override var imageURL: String? = null): EventType {


    constructor(from: EventType?): this() {
        from?.let { event ->
            this.name = event.name
            this.description = event.description
            this.detailURL = event.detailURL
            this.imageURL = event.imageURL
        }
    }
}