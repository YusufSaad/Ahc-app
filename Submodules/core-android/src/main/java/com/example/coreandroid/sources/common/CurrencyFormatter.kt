package com.example.coreandroid.sources.common

import java.text.NumberFormat
import java.util.*

/**
 * Created by ahmedsaad on 2018-01-18.
 * Copyright © 2017. All rights reserved.
 */
class CurrencyFormatter(locale: Locale, fractionDigits: Int) {
    private val formatter: NumberFormat = NumberFormat.getCurrencyInstance(locale)
    private var autoTruncate = false

    constructor(locale: Locale, autoTruncate: Boolean): this(locale,0) {
        this.autoTruncate = autoTruncate
    }

    init {
        formatter.minimumFractionDigits = fractionDigits
        formatter.maximumFractionDigits = fractionDigits
    }

    fun string(cents: Int): String {
        if(autoTruncate) {
            val fractionDigits = if (cents % 100 == 0) 0 else 2
            formatter.minimumFractionDigits = fractionDigits
            formatter.maximumFractionDigits = fractionDigits
        }

        return formatter.format(cents.toFloat() / 100) ?: "${cents / 100}"
    }

}