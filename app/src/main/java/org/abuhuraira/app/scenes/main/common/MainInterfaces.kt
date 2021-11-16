package org.abuhuraira.app.scenes.main.common

import android.support.design.widget.TabLayout
import android.view.View

/**
 * Created by ahmedsaad on 2018-01-18.
 * Copyright © 2017. All rights reserved.
 */

interface NavigationPagerAdapter {
    fun getTabView(position: Int, isSelected: Boolean = false): View
    fun updateTabView(tab: TabLayout.Tab?, isSelected: Boolean = false)
}