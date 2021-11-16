package com.example.ahcstores.sources.stores.videos.models

/**
 * Created by ahmedsaad on 2018-03-09.
 * Copyright © 2018. All rights reserved.
 */
sealed class VideoModels {
    class YoutubeRequest(
            val channelId: String = "UCP9ej92hIt-X16--0_MMwPA", // Default to ahc channel
            val maxResults: Int = 50,
            val pageToken: String? = null
    ): VideoModels()

    class YoutubeSearchRequest(
            val query: String,
            val channelId: String = "UCP9ej92hIt-X16--0_MMwPA", // Default to ahc channel
            val maxResults: Int = 50,
            val pageToken: String? = null
    ): VideoModels()
}