package com.example.ahcstores.sources.extensions

import com.batoulapps.adhan.data.DateComponents
import java.util.*

fun DateComponents.toDate(): Date {
    val calender = Calendar.getInstance()
    calender.set(this.year, this.month - 1, this.day)

    return calender.time
}