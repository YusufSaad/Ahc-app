package org.abuhuraira.app.scenes.listCharities.common

import com.example.ahcstores.sources.stores.charities.models.CharityType
import org.abuhuraira.app.common.dataAdapters.CharityDataModel

sealed class ListCharitiesModels {
    class Request: ListCharitiesModels()

    class Response(
            val charities: List<CharityType>): ListCharitiesModels()

    class ViewModel(
            var charities: List<CharityDataModel.ViewModel>): ListCharitiesModels()

}