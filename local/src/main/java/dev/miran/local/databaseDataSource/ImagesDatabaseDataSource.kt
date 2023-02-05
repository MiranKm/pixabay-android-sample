package dev.miran.local.databaseDataSource

import dev.miran.local.dao.ImagesDao
import dev.miran.repository.local.dataSource.ImageLocalDataSource
import dev.miran.repository.local.dto.HitsItemLocalDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImagesDatabaseDataSource @Inject constructor(
    private val imagesDao: ImagesDao
) : ImageLocalDataSource {

    override suspend fun getAllImages(): Flow<List<HitsItemLocalDto>> =
        imagesDao.getAllImages()


    override suspend fun updateLocalImages(values: List<HitsItemLocalDto>) =
        imagesDao.insertAll(values)

    override fun loadImagesSize(): Int = imagesDao.hitImagesSize()

    override fun clearAllImages() {
        imagesDao.clearAllImages()
    }
}
