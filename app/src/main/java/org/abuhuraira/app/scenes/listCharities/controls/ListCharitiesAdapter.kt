package org.abuhuraira.app.scenes.listCharities.controls

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.abuhuraira.app.R
import org.abuhuraira.app.common.dataAdapters.CharityDataModel
import org.abuhuraira.app.scenes.listCharities.common.CharitiesDataViewDelegate
import kotlinx.android.synthetic.main.recycler_view_list_charities.view.*
import java.lang.ref.WeakReference

/**
 * Created by ahmedsaad on 2018-02-22.
 * Copyright © 2018 Jiffy Inc. All rights reserved.
 */

open class ListCharitiesAdapter(val delegate: WeakReference<CharitiesDataViewDelegate?>? = null)
    : RecyclerView.Adapter<ListCharitiesAdapter.FeaturedViewHolder>() {

    private var viewModels: List<CharityDataModel.ViewModel> = listOf()

    class FeaturedViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount() = viewModels.size

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {
        val model = viewModels[position]

        holder.view.titleTextView.text = model.title

        Glide.with(holder.view.context).load(model.backgroundImage)
                .apply(RequestOptions()
                        .placeholder(R.drawable.maintenance))
                .into(holder.view.imageView)

        holder.view.setOnClickListener { delegate?.get()?.servicesDidSelect(model, this) }
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_list_charities,
                parent, false)
        return FeaturedViewHolder(view)
    }

    fun reloadData(viewModels: List<CharityDataModel.ViewModel>) {
        this.viewModels = viewModels

        notifyDataSetChanged()
        delegate?.get()?.servicesDataViewDidReloadData()
    }
}