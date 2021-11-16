package com.example.ahcstores.sources.stores.charities.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CharityRealmObject(
        override var name: String = "",
        override var description: String? = null,
        @PrimaryKey
        override var donationURL: String = "",
        override var imageURL: String? = null): CharityType, RealmObject() {


    constructor(from: CharityType?): this() {
        from?.let { charity ->
            this.name = charity.name
            this.description = charity.description
            this.donationURL = charity.donationURL
            this.imageURL = charity.imageURL
        }
    }
}