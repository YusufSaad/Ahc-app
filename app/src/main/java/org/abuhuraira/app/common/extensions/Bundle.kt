package org.abuhuraira.app.common.extensions

import android.os.Bundle

/**
 * Created by ahmedsaad on 2018-02-06.
 * Copyright © 2017. All rights reserved.
 */
fun Bundle.putOptionalInt(key: String, value: Int?) {
    this.putInt(key, value ?: -1)
}

fun Bundle.getOptionalInt(key: String, defaultValue: Int? = null): Int? {
    val value: Int = this.getInt(key, defaultValue ?: -1)
    return if (value == -1) null else value
}