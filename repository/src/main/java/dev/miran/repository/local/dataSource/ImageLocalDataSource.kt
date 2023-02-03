package dev.miran.repository.local.dataSource

import dev.miran.repository.local.dto.HitsItemLocalDto
import kotlinx.coroutines.flow.Flow

interface ImageLocalDataSource {
    suspend fun getAllImages(): Flow<List<HitsItemLocalDto>>
    suspend fun updateLocalImages(values: List<HitsItemLocalDto>)
    fun loadImagesSize(): Int
}