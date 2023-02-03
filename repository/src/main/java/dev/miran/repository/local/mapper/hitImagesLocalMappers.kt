package dev.miran.repository.local.mapper

import dev.miran.entity.HitsItem
import dev.miran.repository.local.dto.HitsItemLocalDto

internal fun HitsItemLocalDto.toEntity(): HitsItem = HitsItem(
    id,
    webformatHeight,
    imageWidth,
    previewHeight,
    webformatURL,
    userImageURL,
    previewURL,
    comments,
    type,
    imageHeight,
    tags,
    previewWidth,
    downloads,
    collections,
    userId,
    largeImageURL,
    pageURL,
    imageSize,
    webformatWidth,
    user,
    views,
    likes
)


internal fun List<HitsItemLocalDto>.toEntity():List<HitsItem> = this.map { it.toEntity() }


internal fun HitsItem.toLocalDTO() = HitsItemLocalDto(
   id, webformatHeight, imageWidth, previewHeight, webformatURL, userImageURL, previewURL, comments, type, imageHeight, tags, previewWidth, downloads, collections, userId, largeImageURL, pageURL, imageSize, webformatWidth, user, views, likes
)
internal fun List<HitsItem>.toLocalDTO() = map { it.toLocalDTO() }
