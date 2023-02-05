package dev.miran.repository.remote.dto

import com.squareup.moshi.Json

data class ImageListResponseRemoteDto(

    @Json(name="hits")
	val hits: List<HitsItemRemoteDto> = emptyList(),

    @Json(name="total")
	val total: Int = 0,

    @Json(name="totalHits")
	val totalHits: Int = 0
)