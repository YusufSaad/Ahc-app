package com.example.ahcstores.sources.stores.videos

import com.example.ahcstores.sources.stores.videos.models.VideoModels
import com.example.ahcstores.sources.stores.videos.models.YoutubeVideo
import com.example.ahcstores.sources.stores.videos.models.YoutubeVideosWrapper
import com.example.coreandroid.sources.ConstantsType
import com.example.coreandroid.sources.common.CompletionResponse.Companion.failure
import com.example.coreandroid.sources.common.CompletionResponse.Companion.success
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.common.initDataError
import com.example.coreandroid.sources.errors.DataError
import com.example.coreandroid.sources.extensions.callInBackgroundWithCompletionOnUi
import com.example.coreandroid.sources.logging.LogHelper
import com.example.coreandroid.sources.network.HTTPServiceType
import com.example.coreandroid.sources.preferences.PreferencesWorkerType
import com.google.gson.Gson

class VideosNetworkStore(val httpService: HTTPServiceType,
                         val preferencesWorker: PreferencesWorkerType,
                         val constants: ConstantsType): VideosStore {

    override fun fetch(request: VideoModels.YoutubeRequest, completion: Result<List<YoutubeVideo>, DataError>) {
        searchYoutube(request, completion = completion)
    }

    override fun search(request: VideoModels.YoutubeSearchRequest, completion: Result<List<YoutubeVideo>, DataError>) {
        searchYoutube(request, completion = completion)
    }

    private fun searchYoutube(request: VideoModels, completion: Result<List<YoutubeVideo>, DataError>) {
        val parameters = when (request) {
            is VideoModels.YoutubeSearchRequest -> mapOf(
                    Pair("key", constants.youtubeAPIKey),
                    Pair("channelId", request.channelId),
                    Pair("part", "snippet,id"),
                    Pair("order", "date"),
                    Pair("maxResults", request.maxResults),
                    Pair("pageToken", request.pageToken),
                    Pair("q", request.query)
            )
            is VideoModels.YoutubeRequest -> mapOf(
                    Pair("key", constants.youtubeAPIKey),
                    Pair("channelId", request.channelId),
                    Pair("part", "snippet,id"),
                    Pair("order", "date"),
                    Pair("maxResults", request.maxResults),
                    Pair("pageToken", request.pageToken)
            )
        }

        httpService.get(
                url = preferencesWorker.youtubeURL + "/search",
                parameters = parameters
        ) {
            if (it.value == null || !it.isSuccess) {
                val exception = initDataError(it.error)
                completion(failure(exception))
                LogHelper.e(messages = *arrayOf("An error occurred while fetching youtube videos: " +
                        exception.localizedMessage))

                return@get
            }

            try {
                // Parse response data
                callInBackgroundWithCompletionOnUi(completion) { tcs ->
                    val payload = Gson().fromJson(it.value?.data, YoutubeVideosWrapper::class.java).videos
                    tcs.setResult(payload)
                }
            } catch (e: Exception) {
                completion(failure(DataError.ParseFailure(e)))
                LogHelper.e(messages = *arrayOf("An error occurred while parsing youtube video search: " +
                        e.localizedMessage))
                return@get
            }
        }

    }

}