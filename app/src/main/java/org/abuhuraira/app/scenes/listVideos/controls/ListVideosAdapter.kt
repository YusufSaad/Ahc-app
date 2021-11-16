package org.abuhuraira.app.scenes.listVideos.controls

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.abuhuraira.app.R
import org.abuhuraira.app.common.dataAdapters.VideoDataModel
import org.abuhuraira.app.scenes.listVideos.common.VideosDataViewDelegate
import kotlinx.android.synthetic.main.recycler_view_list_videos.view.*
import java.lang.ref.WeakReference

/**
 * Created by ahmedsaad on 2018-02-22.
 * Copyright © 2018 Jiffy Inc. All rights reserved.
 */

open class ListVideosAdapter(val delegate: WeakReference<VideosDataViewDelegate?>? = null)
    : RecyclerView.Adapter<ListVideosAdapter.FeaturedViewHolder>() {

    private var viewModels: List<VideoDataModel.ViewModel> = listOf()

    class FeaturedViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount() = viewModels.size

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {
        val model = viewModels[position]

        holder.view.titleTextView.text = model.title
        holder.view.contentTextView.text = model.content
        holder.view.publishedAtTextView.text = model.publishedAt

        Glide.with(holder.view.context).load(model.thumbnailImage)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_thumbnail))
                .into(holder.view.thumbnailView)

        holder.view.setOnClickListener { delegate?.get()?.videosDidSelect(model, this) }
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_list_videos,
                parent, false)
        return FeaturedViewHolder(view)
    }

    fun reloadData(viewModels: List<VideoDataModel.ViewModel>) {
        this.viewModels = viewModels

        notifyDataSetChanged()
        delegate?.get()?.videosDataViewDidReloadData()
    }
}