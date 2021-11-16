package com.example.coreandroid.sources.controls

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

/**
 * Created by ahmedsaad on 2018-02-06.
 * Copyright © 2017. All rights reserved.
 */
interface ControllerService {
    fun onAttach(context: Context?)
    fun onCreate(savedInstanceState: Bundle?) {}
    fun onActivityCreated(savedInstanceState: Bundle?) {}
    fun onStart() {}
    fun onViewCreated(view: View, savedInstanceState: Bundle?) {}
    fun onResume() {}
    fun onSaveInstanceState(outState: Bundle) {}
    fun onPause() {}
    fun onStop() {}
    fun onDestroyView() {}
    fun onDestroy() {}
    fun onDetach() {}
}

open class PluggableController: Fragment() {

    open lateinit var services: ArrayList<ControllerService>

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        services.forEach { it.onAttach(context) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        services.forEach { it.onCreate(savedInstanceState) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        services.forEach { it.onActivityCreated(savedInstanceState) }
    }

    override fun onStart() {
        super.onStart()
        services.forEach { it.onStart() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        services.forEach { it.onViewCreated(view, savedInstanceState) }
    }

    override fun onResume() {
        super.onResume()
        services.forEach { it.onResume() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        services.forEach { it.onSaveInstanceState(outState) }
    }

    override fun onPause() {
        super.onPause()
        services.forEach { it.onPause() }
    }

    override fun onStop() {
        super.onStop()
        services.forEach { it.onStop() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        services.forEach { it.onDestroyView() }
    }

    override fun onDestroy() {
        super.onDestroy()
        services.forEach { it.onDestroy() }
    }

    override fun onDetach() {
        super.onDetach()
        services.forEach { it.onDetach() }
    }
}