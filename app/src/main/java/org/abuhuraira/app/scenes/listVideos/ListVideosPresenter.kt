package org.abuhuraira.app.scenes.listVideos

import com.example.coreandroid.sources.errors.DataError
import org.abuhuraira.app.R
import org.abuhuraira.app.common.cleanBase.AppModels
import org.abuhuraira.app.common.dataAdapters.VideoDataModel
import org.abuhuraira.app.common.extensions.getString
import org.abuhuraira.app.scenes.listVideos.common.ListVideosDisplayable
import org.abuhuraira.app.scenes.listVideos.common.ListVideosModels
import org.abuhuraira.app.scenes.listVideos.common.ListVideosPresentable
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ahmedsaad on 2017-10-27.
 * Copyright © 2017. All rights reserved.
 */

class ListVideosPresenter(private val fragment: WeakReference<ListVideosDisplayable?>) : ListVideosPresentable {

    private val dateFormatter = SimpleDateFormat("MMMM d yyyy", Locale.US)

    override fun presentVideos(response: ListVideosModels.Response) {
        val viewModel = ListVideosModels.ViewModel(
                videos = response.videos.fold(arrayListOf()) { acc, videoType ->
                    val video = VideoDataModel.ViewModel(
                            title = videoType.snippet.title,
                            thumbnailImage = videoType.snippet.thumbnails.high.url,
                            id = videoType.id.videoId ?: videoType.id.playlistId ?: "",
                            content = videoType.snippet.description,
                            publishedAt = dateFormatter.format(videoType.snippet.publishedAt)
                    )

                    if (!video.id.isEmpty()) {
                        acc.add(video)
                    }
                    acc
                }
        )

        fragment.get()?.displayFetchedVideos(viewModel)
    }

    override fun presentVideos(error: DataError) {
        // Handle and parse error
        val viewModel = AppModels.Error(
                title = getString(R.string.generic_error_title),
                message = getString(R.string.generic_error_message)
        )

        fragment.get()?.displaySupport(viewModel)
    }

}