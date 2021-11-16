package org.abuhuraira.app.scenes.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.MenuItem
import org.abuhuraira.app.scenes.main.controls.MainPagerAdapter
import org.abuhuraira.app.R
import org.abuhuraira.app.common.activities.BaseActivity
import org.abuhuraira.app.common.protocols.NavigationInterface
import com.example.coreandroid.sources.dependencies.HasDependencies
import com.example.coreandroid.sources.preferences.PreferencesWorkerType
import org.abuhuraira.app.common.TAB_SELECTED
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference


class MainActivity: BaseActivity(), HasDependencies {

    companion object {
        val fragmentBackStack = arrayListOf<WeakReference<Fragment>>()
    }

    private lateinit var mMainPagerAdapter: MainPagerAdapter

    private val preferencesWorker: PreferencesWorkerType by lazy {
        dependencies.resolvePreferencesWorker()
    }

    private var routeListener: ((Fragment) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // Make sure this is before calling super.onCreate
        setTheme(R.style.AppTheme_NoActionBar)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        mMainPagerAdapter = MainPagerAdapter(this.applicationContext, supportFragmentManager)

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            var tabUnselected: TabLayout.Tab? = null

            override fun onTabReselected(tab: TabLayout.Tab?) {
                onTabSelected(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.apply {
                    mMainPagerAdapter.updateTabView(tab, isSelected = false)
                    tabUnselected = tab
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.apply {
                    // Make sure everything is up to date
                    supportFragmentManager.executePendingTransactions()

                    mMainPagerAdapter.updateTabView(tab, isSelected = true)

                    val ft = supportFragmentManager
                            .beginTransaction()

                    for (i in 0 until mMainPagerAdapter.count) {
                        val fragmentTag = mMainPagerAdapter.getItem(i)::class.java.simpleName
                        val fragment = supportFragmentManager.findFragmentByTag(fragmentTag) ?:  mMainPagerAdapter.getItem(i)

                        if (fragment.isAdded && i == tab.position) {
                            ft.show(fragment)
                        } else if (i == tab.position) {
                            ft.add(R.id.main_fragment, fragment, fragment::class.java.simpleName)
                        } else if (fragment.isAdded && i != tab.position) {
                            ft.hide(fragment)
                        }
                    }

                    ft.commit()

                    if (routeListener != null) {
                        // Make sure everything is up to date
                        supportFragmentManager.executePendingTransactions()
                        routeListener?.invoke(mMainPagerAdapter.getItem(tab.position))
                        routeListener = null
                    }
                }
            }

        })

        val selectedTab = savedInstanceState?.getInt(TAB_SELECTED, 0) ?: 0

        for (i in 0 until mMainPagerAdapter.count) {
            tabs.addTab(tabs.newTab(), i, i == selectedTab)
        }

        for (i in 0 until tabs.tabCount) {
            tabs.getTabAt(i)?.customView = mMainPagerAdapter.getTabView(i, isSelected = i == selectedTab)
        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(TAB_SELECTED, tabs.selectedTabPosition)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        when (id) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    override fun onBackPressed() {
        val fragment = fragmentBackStack.firstOrNull {
            val fragment = it.get()
            fragment != null && fragment.isAdded && fragment.isVisible
        }?.get()

        when (fragment) {
            is NavigationInterface -> {
                fragment.onBackPressed()
            }
            else -> { super.onBackPressed() }
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)

        synchronized(MainActivity::class.java) {
            fragmentBackStack.add(WeakReference(fragment))
        }
    }

    override fun onResume() {
        super.onResume()

        if (intent.action == Intent.ACTION_VIEW && intent.data != null) {
            val data = intent.data ?: return

            // TODO: Add logic
            when {
                data.path.startsWith(prefix = "") -> {
                    try {
                    } catch(e: Exception) { /* TODO: Load url through browser */ }
                }
            }

            intent.data = null
        }
    }
}
