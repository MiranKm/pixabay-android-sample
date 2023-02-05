package dev.miran.repository

import dev.miran.entity.HitsItem
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun getImageByQuery(value: String, imageType:String?)
    suspend fun getImageById(id: Int): List<HitsItem>
    suspend fun loadImages(): Flow<List<HitsItem>>
    fun loadImagesSize(): Int
    fun clearAllImages()

}