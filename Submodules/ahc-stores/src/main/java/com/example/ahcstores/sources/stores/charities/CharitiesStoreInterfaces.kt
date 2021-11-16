package com.example.ahcstores.sources.stores.charities

import com.example.ahcstores.sources.stores.charities.models.CharityType
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.errors.DataError


/**
 * Created by ahmedsaad on 2017-10-30.
 * Copyright © 2017. All rights reserved.
 */

interface CharitiesStore {
    fun fetch(completion: Result<List<CharityType>, DataError>)
}

interface CharitiesCacheStore: CharitiesStore {
    fun createOrUpdate(request: CharityType, completion: Result<CharityType, DataError>)
}

interface CharitiesWorkerType: CharitiesStore