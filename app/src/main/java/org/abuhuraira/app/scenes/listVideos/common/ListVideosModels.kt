package org.abuhuraira.app.scenes.listVideos.common

import com.example.ahcstores.sources.stores.videos.models.YoutubeVideo
import org.abuhuraira.app.common.dataAdapters.VideoDataModel

sealed class ListVideosModels {
    class Request(): ListVideosModels()

    class SearchRequest(
            val query: String? = null): ListVideosModels()

    class Response(
            val videos: List<YoutubeVideo>): ListVideosModels()

    class ViewModel(
            var videos: List<VideoDataModel.ViewModel>): ListVideosModels()

}