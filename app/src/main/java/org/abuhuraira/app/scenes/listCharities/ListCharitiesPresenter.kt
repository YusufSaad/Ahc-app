package org.abuhuraira.app.scenes.listCharities

import com.example.coreandroid.sources.errors.DataError
import org.abuhuraira.app.R
import org.abuhuraira.app.common.cleanBase.AppModels
import org.abuhuraira.app.common.dataAdapters.CharityDataModel
import org.abuhuraira.app.common.extensions.getString
import org.abuhuraira.app.scenes.listCharities.common.ListCharitiesDisplayable
import org.abuhuraira.app.scenes.listCharities.common.ListCharitiesModels
import org.abuhuraira.app.scenes.listCharities.common.ListCharitiesPresentable
import java.lang.ref.WeakReference

/**
 * Created by ahmedsaad on 2017-10-27.
 * Copyright © 2017. All rights reserved.
 */

class ListCharitiesPresenter(private val fragment: WeakReference<ListCharitiesDisplayable?>) : ListCharitiesPresentable {


    override fun presentCharities(response: ListCharitiesModels.Response) {
        val viewModel = ListCharitiesModels.ViewModel(
                charities = response.charities.fold(arrayListOf()) { acc, charityType ->
                    val charity = CharityDataModel.ViewModel(
                            title = charityType.name,
                            donationURL = charityType.donationURL,
                            backgroundImage = charityType.imageURL,
                            content = charityType.description
                    )
                    acc.add(charity)
                    acc
                }
        )

        fragment.get()?.displayFetchedCharities(viewModel)
    }

    override fun presentCharities(error: DataError) {
        // Handle and parse error
        val viewModel = AppModels.Error(
                title = getString(R.string.generic_error_title),
                message = getString(R.string.generic_error_message)
        )

        fragment.get()?.displaySupport(viewModel)
    }

}