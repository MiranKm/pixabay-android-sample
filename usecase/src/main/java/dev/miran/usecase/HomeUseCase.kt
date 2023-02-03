package dev.miran.usecase

import dev.miran.entity.HitsItem
import dev.miran.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class HomeUseCase @Inject constructor(
    private val repository: ImageRepository
) {

    suspend fun getImageByQuery(query: String) = repository.getImageByQuery(query)
    suspend fun loadImages(): Flow<List<HitsItem>> = repository.loadImages()

    suspend fun initialLoad(query: String) {
        val currentSize = repository.loadImagesSize()
        if (currentSize == 0) {
            getImageByQuery(query)
        }
    }
}