package org.abuhuraira.app.common.controls.homeWidget

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import org.abuhuraira.app.R
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.view_home_widget.view.*
import org.abuhuraira.app.common.extensions.isHidden


/**
 * Created by ahmedsaad on 2018-02-22.
 * Copyright © 2018 Jiffy Inc. All rights reserved.
 */
class HomeWidgetView(context: Context, attrs: AttributeSet?): LinearLayout(context, attrs) {
    constructor(context: Context): this(context, null)

    var adapter: RecyclerView.Adapter<*>? = null
        set(value) {
            field = value
            recyclerView.adapter = value
        }

    init {
        val a = context.obtainStyledAttributes(attrs,
                R.styleable.HomeWidgetView, 0, 0)
        val titleText = a.getString(R.styleable.HomeWidgetView_titleText)
        val subTitleText = a.getString(R.styleable.HomeWidgetView_subTitleText)
        val numColumns = a.getInteger(R.styleable.HomeWidgetView_numColumns, 1)
        val layoutManager = a.getInt(R.styleable.HomeWidgetView_recyclerViewManager, 1)
        a.recycle()

        orientation = LinearLayout.VERTICAL

        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_home_widget, this, true)

        textView.text = titleText

        subTitleTextView.text = subTitleText
        subTitleTextView.isHidden = subTitleText.isNullOrBlank()

        recyclerView.layoutManager = when (layoutManager) {
            2 -> LinearLayoutManager(context, HORIZONTAL, false)
            3 -> GridLayoutManager(context, numColumns)
            else -> LinearLayoutManager(context)
        }

    }

    fun setHasFixedSize(value: Boolean) {
        recyclerView.setHasFixedSize(value)
    }
}