package dev.miran.repository

import dev.miran.entity.HitsItem
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun getImageByQuery(value:String)
    suspend fun loadImages(): Flow<List<HitsItem>>
     fun loadImagesSize(): Int
}