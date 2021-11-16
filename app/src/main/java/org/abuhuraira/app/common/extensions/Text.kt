package org.abuhuraira.app.common.extensions

import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import java.util.*


/**
 * Created by ahmedsaad on 2018-03-09.
 * Copyright © 2018. All rights reserved.
 */

/**
 * A Debounced TextWatcher
 * Rejects clicks that are too close together in time.
 * This class is safe to use as an OnClickListener for multiple views, and will debounce each one separately.
 */
interface DebouncedTextWatcher
/**
 * The one and only constructor
 * @param minimumIntervalMsec The minimum allowed time between clicks - any click sooner than this after a previous click will be rejected
 */
: TextWatcher {
    companion object {
        private var lastSearch: Long? = null
        private val timer = Timer()
        private val tasks = arrayListOf<TimerTask>()
        private val minimumInterval: Long = 500L
    }

    fun debouncedBeforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    fun debouncedOnTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    fun debouncedAfterTextChanged(p0: Editable?) {}

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        val previousClickTimestamp = lastSearch
        val currentTimestamp = SystemClock.uptimeMillis()

        if (previousClickTimestamp == null || currentTimestamp - previousClickTimestamp.toLong() > minimumInterval) {
            debouncedBeforeTextChanged(p0, p1, p2, p3)
        }
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        val previousClickTimestamp = lastSearch
        val currentTimestamp = SystemClock.uptimeMillis()

        if (previousClickTimestamp == null || currentTimestamp - previousClickTimestamp.toLong() > minimumInterval) {
            debouncedOnTextChanged(p0, p1, p2, p3)
        }
    }

    override fun afterTextChanged(p0: Editable?) {
        tasks.forEach { it.cancel() }
        tasks.clear()
        timer.purge()

        val currentTimestamp = SystemClock.uptimeMillis()
        val previousClickTimestamp = lastSearch ?: currentTimestamp
                lastSearch = currentTimestamp

        if (currentTimestamp - previousClickTimestamp > minimumInterval) {
            debouncedAfterTextChanged(p0)
            lastSearch = null
        } else {
            val task = object: TimerTask() {
                override fun run() {
                    debouncedAfterTextChanged(p0)
                    lastSearch = null
                }
            }
            tasks.add(task)

            timer.schedule(task, minimumInterval - (currentTimestamp - previousClickTimestamp))
        }
    }
}