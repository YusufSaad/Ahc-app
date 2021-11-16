package com.example.coreandroid.sources.common

/**
 * Created by ahmedsaad on 2018-01-09.
 * Copyright © 2017. All rights reserved.
 */
class DispatchGroup {

    private var count = 0
    private var runnable: Runnable? = null

    init {
        count = 0
    }

    @Synchronized
    fun enter() {
        count++
    }

    @Synchronized
    fun leave() {
        count--
        notifyGroup()
    }

    fun notify(r: Runnable) {
        runnable = r
        notifyGroup()
    }

    private fun notifyGroup() {
        if (count <= 0 && runnable != null) {
            runnable!!.run()
        }
    }
}