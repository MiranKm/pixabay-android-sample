package dev.miran.repository.remote.mapper

import dev.miran.entity.HitsItem
import dev.miran.repository.remote.dto.HitsItemRemoteDto


fun HitsItemRemoteDto.toEntity(): HitsItem = HitsItem(
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


fun List<HitsItemRemoteDto>.toEntity():List<HitsItem> = this.map { it.toEntity() }