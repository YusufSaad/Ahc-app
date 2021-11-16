package org.abuhuraira.app.common.dataAdapters

sealed class VideoDataModel {
    class ViewModel(
            val content: String?,
            val title: String,
            val thumbnailImage: String?,
            val id: String,
            val publishedAt: String): VideoDataModel()
}