package com.example.coreandroid.sources.extensions

import android.util.Patterns

/**
 * Created by ahmedsaad on 2018-01-15.
 * Copyright © 2017. All rights reserved.
 */

fun String.isEmail(): Boolean {
    // Check pattern for email
    val p = Patterns.EMAIL_ADDRESS
    val m = p.matcher(this)

    return m.matches()
}