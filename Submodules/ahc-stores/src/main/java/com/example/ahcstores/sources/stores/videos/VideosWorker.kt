package com.example.ahcstores.sources.stores.videos

import com.example.ahcstores.sources.stores.videos.models.VideoModels
import com.example.ahcstores.sources.stores.videos.models.YoutubeVideo
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.errors.DataError

class VideosWorker(val store: VideosStore): VideosWorkerType {

    override fun fetch(request: VideoModels.YoutubeRequest, completion: Result<List<YoutubeVideo>, DataError>) {
        store.fetch(request, completion = completion)
    }

    override fun search(request: VideoModels.YoutubeSearchRequest, completion: Result<List<YoutubeVideo>, DataError>) {
        store.search(request, completion = completion)
    }
}