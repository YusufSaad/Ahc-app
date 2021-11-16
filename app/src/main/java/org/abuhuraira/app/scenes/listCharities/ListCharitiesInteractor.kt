package org.abuhuraira.app.scenes.listCharities

import com.example.ahcstores.sources.stores.charities.CharitiesWorkerType
import com.example.coreandroid.sources.errors.DataError
import org.abuhuraira.app.scenes.listCharities.common.ListCharitiesBusinessLogic
import org.abuhuraira.app.scenes.listCharities.common.ListCharitiesModels
import org.abuhuraira.app.scenes.listCharities.common.ListCharitiesPresentable

/**
 * Created by ahmedsaad on 2017-10-27.
 * Copyright © 2017. All rights reserved.
 */

class ListCharitiesInteractor(val presenter: ListCharitiesPresentable,
                              val charitiesWorker: CharitiesWorkerType) : ListCharitiesBusinessLogic {

    override fun fetchCharities(request: ListCharitiesModels.Request) {
        charitiesWorker.fetch {
            val charities = it.value
            if (charities == null || !it.isSuccess) {
                this.presenter.presentCharities(error = it.error
                        ?: DataError.UnknownReason(null))
                return@fetch
            }

            this.presenter.presentCharities(
                    ListCharitiesModels.Response(
                            charities = charities
                    )
            )
        }
    }
}