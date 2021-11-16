package com.example.coreandroid.sources.extensions

import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by ahmedsaad on 2018-04-03.
 * Copyright © 2018. All rights reserved.
 */
fun JSONObject.merge(parameters: JSONObject, call: (old: JSONObject, new: JSONObject) -> JSONObject) {
    parameters.keys().forEach {
        if (this.has(it)) {
            this.put(it, call(this, parameters).get(it))
        } else {
            this.put(it, parameters.get(it))
        }
    }
}

fun JSONArray.removeAll() {
    if (this.length() > 0)
        (0 until this.length()).forEach { this.remove(it) }
}