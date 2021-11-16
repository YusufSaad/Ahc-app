package com.example.ahcstores.sources.stores.videos

import com.example.ahcstores.sources.stores.videos.models.VideoModels
import com.example.ahcstores.sources.stores.videos.models.YoutubeVideo
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.errors.DataError


/**
 * Created by ahmedsaad on 2017-10-30.
 * Copyright © 2017. All rights reserved.
 */

interface VideosStore {
    fun fetch(request: VideoModels.YoutubeRequest = VideoModels.YoutubeRequest(),
              completion: Result<List<YoutubeVideo>, DataError>)

    fun search(request: VideoModels.YoutubeSearchRequest,
               completion: Result<List<YoutubeVideo>, DataError>)
}

interface VideosWorkerType: VideosStore