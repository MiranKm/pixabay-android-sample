package dev.miran.usecase

import dev.miran.entity.HitsItem
import dev.miran.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class HomeUseCase @Inject constructor(
    private val repository: ImageRepository
) {

    suspend fun getImageByQuery(query: String, imageType:String?) = repository.getImageByQuery(query, imageType)
    suspend fun loadImages(): Flow<List<HitsItem>> = repository.loadImages()
    fun clearAllImages() = repository.clearAllImages()

    suspend fun initialLoad(query: String, imageType: String?) {
        val currentSize = repository.loadImagesSize()
        if (currentSize == 0) {
            getImageByQuery(query,imageType )
        }
    }
}