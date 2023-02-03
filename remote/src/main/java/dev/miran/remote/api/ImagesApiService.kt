package dev.miran.remote.api

import dev.miran.remote.BuildConfig
import dev.miran.remote.api.ImagesApiService.Companion.API_KEY
import dev.miran.repository.remote.dto.ImageResponseRemoteDto
import retrofit2.Response
import retrofit2.http.*

interface ImagesApiService {

    @GET("$BASE_URL/api/?key=$API_KEY&")
    suspend fun getImages(@Query("q") query: String): Response<ImageResponseRemoteDto>

    private companion object {
        const val BASE_URL = BuildConfig.API_BASE_URL
        const val API_KEY = BuildConfig.API_KEY
    }
}