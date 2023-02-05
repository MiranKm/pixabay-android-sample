package dev.miran.entity
data class HitsItem(
	val id: Int,
	val webformatHeight: Int? = null,
	val imageWidth: Int? = null,
	val previewHeight: Int? = null,
	val webformatURL: String? = null,
	val userImageURL: String? = null,
	val previewURL: String? = null,
	val comments: Int? = null,
	val type: String? = null,
	val imageHeight: Int? = null,
	val tags: String="",
	val previewWidth: Int? = null,
	val downloads: Int? = null,
	val collections: Int? = null,
	val userId: Int? = null,
	val largeImageURL: String? = null,
	val pageURL: String? = null,
	val imageSize: Int? = null,
	val webformatWidth: Int? = null,
	val user: String,
	val views: Int? = null,
	val likes: Int? = null
)

