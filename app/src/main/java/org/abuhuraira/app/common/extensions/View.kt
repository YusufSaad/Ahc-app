package org.abuhuraira.app.common.extensions

import android.view.View
import android.app.Activity
import android.graphics.Rect
import android.support.v4.widget.NestedScrollView
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.LinearLayout
import android.os.SystemClock
import org.abuhuraira.app.R
import org.abuhuraira.app.common.Application
import java.util.*


/**
 * Created by ahmedsaad on 2018-01-30.
 * Copyright © 2017. All rights reserved.
 */
var View.isHidden: Boolean
    get() = this.visibility != View.VISIBLE
    set(value) {
        this.visibility = if (value) View.GONE else View.VISIBLE
    }

var View.isVisible: Boolean
    get() = this.visibility == View.VISIBLE
    set(value) {
        this.visibility = if (value) View.VISIBLE else View.INVISIBLE
    }

var View.isHiddenAnimated: Boolean
    get() = this.visibility != View.VISIBLE
    set(value) {
        if (value) this.collapse() else this.expand()
    }

/**
 * Helper method to animate the expand of any view
 * @param v - View to be expanded.
 */
fun View.expand() {
    if (this.visibility == View.VISIBLE)
        return

    this.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    val targetHeight = this.measuredHeight

    // Older versions of android (pre API 21) cancel animations for views with a height of 0.
    this.layoutParams.height = 1
    this.visibility = View.VISIBLE

    val view = this
    val a = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            view.layoutParams.height = if (interpolatedTime == 1f)
                LinearLayout.LayoutParams.WRAP_CONTENT
            else
                (targetHeight * interpolatedTime).toInt()
            view.requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    // 1dp/ms
    a.duration = (targetHeight / this.context.resources.displayMetrics.density).toLong()
    this.startAnimation(a)

    // TODO: Fix smooth scroll for expanding views
    val i = IntArray(2)
    val scrollBounds = Rect()
    this.getHitRect(scrollBounds)
    this.getLocationOnScreen(i)

    val sv = this.rootView.findViewById(R.id.scrollView) as? NestedScrollView
    sv?.smoothScrollBy(0, this.scrollY + (i[1] - scrollBounds.bottom))
}

/**
 * Helper method that animates the collapse of any view
 * @param v View to be collapsed.
 */
fun View.collapse() {
    if (this.visibility == View.GONE)
        return

    val initialHeight = this.measuredHeight

    val view = this
    val a = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            if (interpolatedTime == 1f) {
                view.visibility = View.GONE
            } else {
                view.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                view.requestLayout()
            }
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    // 1dp/ms
    a.duration = (initialHeight / this.context.resources.displayMetrics.density).toLong()
    this.startAnimation(a)
}

fun getLaunchActivity(): Activity? {
    val app = Application.instance
    val launcherIntent = app.packageManager.getLaunchIntentForPackage(app.packageName)
    val launchActivityInfo = launcherIntent.resolveActivityInfo(app.packageManager, 0)
    val clazz: Class<*>?
    try {
        clazz = Class.forName(launchActivityInfo.name)
        if (clazz != null)
            return Activity::class.java.cast(clazz.newInstance())
    } catch (e: Exception) {
    }

    return null
}

/**
 * A Debounced OnClickListener
 * Rejects clicks that are too close together in time.
 * This class is safe to use as an OnClickListener for multiple views, and will debounce each one separately.
 */
abstract class DebouncedOnClickListener
/**
 * The one and only constructor
 * @param minimumIntervalMsec The minimum allowed time between clicks - any click sooner than this after a previous click will be rejected
 */
(private val minimumInterval: Long) : View.OnClickListener {
    private val lastClickMap: MutableMap<View, Long>

    /**
     * Implement this in your subclass instead of onClick
     * @param v The view that was clicked
     */
    abstract fun onDebouncedClick(v: View)

    init {
        this.lastClickMap = WeakHashMap<View, Long>()
    }

    override fun onClick(clickedView: View) {
        val previousClickTimestamp = lastClickMap[clickedView]
        val currentTimestamp = SystemClock.uptimeMillis()

        lastClickMap[clickedView] = currentTimestamp
        if (previousClickTimestamp == null || currentTimestamp - previousClickTimestamp.toLong() > minimumInterval) {
            onDebouncedClick(clickedView)
        }
    }
}


