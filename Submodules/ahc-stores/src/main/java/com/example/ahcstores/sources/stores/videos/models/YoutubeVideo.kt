package com.example.ahcstores.sources.stores.videos.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Thumbnail(
        val url: String,
        val width: Int,
        val height: Int)

data class Thumbnails (
        val default: Thumbnail,
        val medium: Thumbnail,
        val high: Thumbnail)

data class Id(
        val videoId: String?,
        val playlistId: String?)

data class Snippet(
        val publishedAt: Date,
        val title: String,
        val description: String,
        val thumbnails: Thumbnails)

data class YoutubeVideo(
        val id: Id,
        val snippet: Snippet)

data class YoutubeVideosWrapper(
        @SerializedName("items")
        val videos: List<YoutubeVideo>)