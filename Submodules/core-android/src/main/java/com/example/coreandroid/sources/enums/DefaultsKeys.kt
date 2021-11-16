package com.example.coreandroid.sources.enums

/**
 * Created by ahmedsaad on 2017-11-30.
 * Copyright © 2017. All rights reserved.
 */

/// User defaults keys for strong-typed access.
/// Taken from: https://github.com/radex/SwiftyUserDefaults
open class DefaultsKeys {
    companion object {
        val userID = DefaultsKey<Int?>("userID", 0)
    }
}

open class DefaultsKey<out ValueType>(key: String, default: ValueType): DefaultsKeys() {
    val name: String = key
    val type: ValueType? = default
}