package org.abuhuraira.app.scenes.home.controls

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.abuhuraira.app.R
import org.abuhuraira.app.common.extensions.isHidden
import org.abuhuraira.app.scenes.home.common.HomeModels
import kotlinx.android.synthetic.main.recycler_view_prayer_time.view.*
import org.abuhuraira.app.common.extensions.getString

/**
 * Created by ahmedsaad on 2018-02-22.
 * Copyright © 2018 Jiffy Inc. All rights reserved.
 */

open class PrayerTimeDataViewAdapter: RecyclerView.Adapter<PrayerTimeDataViewAdapter.PrayerTimeViewHolder>() {

    private var viewModels: List<HomeModels.PrayerTimeViewModel> = listOf()

    class PrayerTimeViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount() = viewModels.size + 1

    override fun onBindViewHolder(holder: PrayerTimeViewHolder, position: Int) {
        if (position == 0) {
            holder.view.prayerNameTextView.text = null
            holder.view.prayerTimeTextView.text = getString(R.string.athan_title)
            holder.view.iqamaTimeTextView.text = getString(R.string.iqama_title)

            holder.view.prayerTimeTextView.isHidden = false
            holder.view.iqamaTimeTextView.isHidden = false
            return
        }
        val model = viewModels[position - 1]

        holder.view.prayerNameTextView.text = model.name
        holder.view.prayerTimeTextView.text = model.athan
        holder.view.iqamaTimeTextView.text = model.iqama

        holder.view.prayerTimeTextView.isHidden = model.athan.isEmpty()
        holder.view.iqamaTimeTextView.isHidden = model.iqama.isEmpty()
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrayerTimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_prayer_time, parent, false)
        return PrayerTimeViewHolder(view)
    }

    fun reloadData(viewModels: List<HomeModels.PrayerTimeViewModel>) {
        this.viewModels = viewModels

        notifyDataSetChanged()
    }
}