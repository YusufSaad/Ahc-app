package com.example.ahcstores.sources.stores.charities

import com.example.ahcstores.sources.stores.charities.models.Charity
import com.example.ahcstores.sources.stores.charities.models.CharityRealmObject
import com.example.ahcstores.sources.stores.charities.models.CharityType
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.errors.DataError
import com.example.coreandroid.sources.extensions.callRealmInBackgroundWithCompletionOnUi

class CharitiesRealmStore: CharitiesCacheStore {

    override fun fetch(completion: Result<List<CharityType>, DataError>) {
        callRealmInBackgroundWithCompletionOnUi(completion = completion) { realm, tcs ->
            val list = realm.where(CharityRealmObject::class.java)
                    .findAll().mapNotNull { Charity(it) }

            tcs.setResult(list)
        }
    }

    override fun createOrUpdate(request: CharityType, completion: Result<CharityType, DataError>) {
        callRealmInBackgroundWithCompletionOnUi(completion = completion) { realm, tcs ->
            realm.executeTransaction {
                realm.insertOrUpdate(CharityRealmObject(request))
            }

            // Get refreshed object to return
            val item = realm.where(CharityRealmObject::class.java)
                    .equalTo("donationURL", request.donationURL)
                    .findFirst()

            if (item == null) {
                tcs.setError(DataError.NonExistent)
            } else {
                tcs.setResult(Charity(item))
            }
        }
    }

}