package org.abuhuraira.app.scenes.home.controls

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.recycler_view_events.view.*
import org.abuhuraira.app.R
import org.abuhuraira.app.common.extensions.isHidden
import org.abuhuraira.app.scenes.home.common.HomeModels
import java.lang.ref.WeakReference

/**
 * Created by ahmedsaad on 2018-02-22.
 * Copyright © 2018 Jiffy Inc. All rights reserved.
 */

open class EventDataViewAdapter(val delegate: WeakReference<EventDataDelegate?>? = null):
        RecyclerView.Adapter<EventDataViewAdapter.PrayerTimeViewHolder>() {

    private var viewModels: List<HomeModels.EventViewModel> = listOf()

    class PrayerTimeViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount() = viewModels.size

    override fun onBindViewHolder(holder: PrayerTimeViewHolder, position: Int) {
        val model = viewModels[position]

        holder.view.titleTextView.text = model.name

        holder.view.titleTextView.isHidden = model.name.isEmpty()

        Glide.with(holder.view.context).load(model.imageURL)
                .apply(RequestOptions()
                        .placeholder(R.drawable.maintenance))
                .into(holder.view.imageView)

        holder.view.setOnClickListener {
            delegate?.get()?.onClick(model)
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrayerTimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_events, parent, false)
        return PrayerTimeViewHolder(view)
    }

    fun reloadData(viewModels: List<HomeModels.EventViewModel>) {
        this.viewModels = viewModels

        notifyDataSetChanged()
    }
}

interface EventDataDelegate {
    fun onClick(viewModel: HomeModels.EventViewModel)
}