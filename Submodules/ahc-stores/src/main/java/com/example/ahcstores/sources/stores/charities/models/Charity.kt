package com.example.ahcstores.sources.stores.charities.models



/**
* Created by ahmedsaad on 2017-10-24.
* Copyright © 2017. All rights reserved.
*/

data class Charity(
        override var name: String = "",
        override var description: String? = null,
        override var donationURL: String = "",
        override var imageURL: String? = null): CharityType {


    constructor(from: CharityType?): this() {
        from?.let { charity ->
            this.name = charity.name
            this.description = charity.description
            this.donationURL = charity.donationURL
            this.imageURL = charity.imageURL
        }
    }
}