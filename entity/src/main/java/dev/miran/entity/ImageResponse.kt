package dev.miran.entity

data class ImageResponse(
	val hits: List<HitsItem?>? = null,
	val total: Int? = null,
	val totalHits: Int? = null
)
