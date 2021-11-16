package org.abuhuraira.app.common.activities

import android.os.Bundle
import android.view.*
import org.abuhuraira.app.R
import org.abuhuraira.app.scenes.home.HomeFragment

/**
 * Created by ahmedsaad on 2018-01-22.
 * Copyright © 2017. All rights reserved.
 */
class BaseHomeFragment: BaseNavigationFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            childFragmentManager
                    .beginTransaction()
                    ?.replace(R.id.base_fragment, HomeFragment(),
                            HomeFragment::class.java.simpleName)
                    ?.addToBackStack(HomeFragment::class.java.simpleName)
                    ?.commit()
        }

    }
}