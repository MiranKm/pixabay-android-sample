package dev.miran.repository.local.dto

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity(tableName = "image_response")
data class ImageResponseLocalDto(

    @Json(name="hits")
	val hits: List<HitsItemLocalDto?>? = null,

    @Json(name="total")
	val total: Int? = null,

    @Json(name="totalHits")
	val totalHits: Int? = null
)