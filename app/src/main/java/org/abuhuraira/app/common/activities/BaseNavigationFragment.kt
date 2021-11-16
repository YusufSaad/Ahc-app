package org.abuhuraira.app.common.activities

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.abuhuraira.app.R
import org.abuhuraira.app.common.protocols.NavigationInterface
import java.lang.ref.WeakReference

abstract class BaseNavigationFragment: Fragment(), NavigationInterface {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_base, container, false)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        childFragmentManager.executePendingTransactions()

        if (!hidden) {
            childFragmentManager.fragments.firstOrNull { fragment ->
                fragment.isAdded && fragment.isVisible
            }?.onHiddenChanged(hidden)
        }
    }

    override fun onBackPressed() {
        if (childFragmentManager.backStackEntryCount > 1)
            childFragmentManager.popBackStack()
    }
}