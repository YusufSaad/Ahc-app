package com.example.coreandroid.sources.logging.destinations

/**
 * Created by ahmedsaad on 2018-04-03.
 * Copyright © 2018. All rights reserved.
 */
interface BaseDestination {
    fun send(level: Int, msg: String, file: String, function: String, line: Int, context: Any? = null): String?
}