package org.abuhuraira.app.common.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import org.abuhuraira.app.R
import org.abuhuraira.app.common.controls.SpinnerDialogFragment
import org.abuhuraira.app.common.protocols.BaseInterface

/**
 * Created by ahmedsaad on 2018-01-18.
 * Copyright © 2017. All rights reserved.
 */
open class BaseFragment: Fragment(), BaseInterface {
    override var spinner: SpinnerDialogFragment? = null

    protected var isFirstRun: Boolean = false

    fun setTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }

    fun setSubTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = title
    }

    fun setHomeAsUp(value: Boolean = false) {

        if (value) {
            (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowHomeEnabled(true)
        } else {
            (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowHomeEnabled(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isFirstRun = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.setSupportActionBar(view.findViewById(R.id.toolbar))

        showTabs()
    }

    override fun onStop() {
        super.onStop()
        isFirstRun = false
    }
}