package org.abuhuraira.app.common.activities

import android.os.Bundle
import android.view.*
import org.abuhuraira.app.R
import org.abuhuraira.app.scenes.listCharities.ListCharitiesFragment

/**
 * Created by ahmedsaad on 2018-01-22.
 * Copyright © 2017. All rights reserved.
 */
class BaseCharityFragment: BaseNavigationFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            childFragmentManager
                    .beginTransaction()
                    ?.replace(R.id.base_fragment, ListCharitiesFragment(),
                            ListCharitiesFragment::class.java.simpleName)
                    ?.addToBackStack(ListCharitiesFragment::class.java.simpleName)
                    ?.commit()
        }

    }
}