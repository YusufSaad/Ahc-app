package org.abuhuraira.app.scenes.listVideos

import com.example.ahcstores.sources.stores.videos.VideosWorkerType
import com.example.ahcstores.sources.stores.videos.models.VideoModels
import com.example.coreandroid.sources.errors.DataError
import org.abuhuraira.app.scenes.listVideos.common.ListVideosBusinessLogic
import org.abuhuraira.app.scenes.listVideos.common.ListVideosModels
import org.abuhuraira.app.scenes.listVideos.common.ListVideosPresentable

/**
 * Created by ahmedsaad on 2017-10-27.
 * Copyright © 2017. All rights reserved.
 */

class ListVideosInteractor(val presenter: ListVideosPresentable,
                           val videosWorker: VideosWorkerType) : ListVideosBusinessLogic {

    override fun fetchVideos(request: ListVideosModels.Request) {
        videosWorker.fetch {
            val videos = it.value
            if (videos == null || !it.isSuccess) {
                this.presenter.presentVideos(error = it.error
                        ?: DataError.UnknownReason(null))
                return@fetch
            }

            this.presenter.presentVideos(
                    ListVideosModels.Response(
                            videos = videos
                    )
            )
        }
    }

    override fun searchVideos(request: ListVideosModels.SearchRequest) {
        val requestModel = VideoModels.YoutubeSearchRequest(
                query = request.query ?: ""
        )

        videosWorker.search(requestModel) {
            val videos = it.value
            if (videos == null || !it.isSuccess) {
                this.presenter.presentVideos(error = it.error
                        ?: DataError.UnknownReason(null))
                return@search
            }

            this.presenter.presentVideos(
                    ListVideosModels.Response(
                            videos = videos
                    )
            )
        }
    }
}