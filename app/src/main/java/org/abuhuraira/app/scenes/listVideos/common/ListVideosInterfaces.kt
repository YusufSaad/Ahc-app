package org.abuhuraira.app.scenes.listVideos.common

import android.support.v7.widget.RecyclerView
import com.example.coreandroid.sources.errors.DataError
import org.abuhuraira.app.common.cleanBase.AppDisplayable
import org.abuhuraira.app.common.dataAdapters.VideoDataModel
import org.abuhuraira.app.common.routers.AppRoutable

/**
* Created by ahmedsaad on 2017-10-24.
* Copyright © 2017. All rights reserved.
*/

interface ListVideosDisplayable: AppDisplayable {
    fun displayFetchedVideos(viewModel: ListVideosModels.ViewModel)
}

interface ListVideosBusinessLogic {
    fun fetchVideos(request: ListVideosModels.Request)
    fun searchVideos(request: ListVideosModels.SearchRequest)
}

interface ListVideosPresentable {
    fun presentVideos(response: ListVideosModels.Response)
    fun presentVideos(error: DataError)
}

interface ListVideosRoutable: AppRoutable {
    fun showVideo(id: String)
}

interface VideosDataViewDelegate {
    fun videosDidSelect(model: VideoDataModel.ViewModel,
                          adapter: RecyclerView.Adapter<*>)
    fun videosDataViewDidReloadData() {}
}