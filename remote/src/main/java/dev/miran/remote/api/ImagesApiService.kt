package dev.miran.remote.api

import dev.miran.remote.BuildConfig
import dev.miran.repository.remote.dto.ImageListResponseRemoteDto
import retrofit2.Response
import retrofit2.http.*

interface ImagesApiService {

    @GET("$BASE_URL/api/?key=$API_KEY&")
    suspend fun getImageByQuery(
        @Query("q") query: String,
        @Query("image_type") imageType: String?
    ): Response<ImageListResponseRemoteDto>

    @GET("$BASE_URL/api/?key=$API_KEY&")
    suspend fun getImageById(@Query("id") id: Int): Response<ImageListResponseRemoteDto>

    private companion object {
        const val BASE_URL = BuildConfig.API_BASE_URL
        const val API_KEY = BuildConfig.API_KEY
    }
}