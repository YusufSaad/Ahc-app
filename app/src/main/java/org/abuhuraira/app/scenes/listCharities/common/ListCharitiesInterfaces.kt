package org.abuhuraira.app.scenes.listCharities.common

import android.support.v7.widget.RecyclerView
import com.example.coreandroid.sources.errors.DataError
import org.abuhuraira.app.common.cleanBase.AppDisplayable
import org.abuhuraira.app.common.dataAdapters.CharityDataModel
import org.abuhuraira.app.common.routers.AppRoutable

/**
* Created by ahmedsaad on 2017-10-24.
* Copyright © 2017. All rights reserved.
*/

interface ListCharitiesDisplayable: AppDisplayable {
    fun displayFetchedCharities(viewModel: ListCharitiesModels.ViewModel)
}

interface ListCharitiesBusinessLogic {
    fun fetchCharities(request: ListCharitiesModels.Request)
}

interface ListCharitiesPresentable {
    fun presentCharities(response: ListCharitiesModels.Response)
    fun presentCharities(error: DataError)
}

interface ListCharitiesRoutable: AppRoutable

interface CharitiesDataViewDelegate {
    fun servicesDidSelect(model: CharityDataModel.ViewModel,
                          adapter: RecyclerView.Adapter<*>)
    fun servicesDataViewDidReloadData() {}
}