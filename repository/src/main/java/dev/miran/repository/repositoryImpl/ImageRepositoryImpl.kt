package dev.miran.repository.repositoryImpl

import dev.miran.entity.HitsItem
import dev.miran.repository.ImageRepository
import dev.miran.repository.local.dataSource.ImageLocalDataSource
import dev.miran.repository.local.mapper.toEntity
import dev.miran.repository.local.mapper.toLocalDTO
import dev.miran.repository.remote.dataSource.ImageRemoteDataSource
import dev.miran.repository.remote.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val apiDataSource: ImageRemoteDataSource,
    private val localDataSource: ImageLocalDataSource,
) : ImageRepository {

    override suspend fun getImageByQuery(value: String, imageType:String?) = localDataSource.updateLocalImages(
        apiDataSource.getImageByQuery(value, imageType).hits.toEntity().toLocalDTO()
    )

    override suspend fun getImageById(id: Int): List<HitsItem> =
        apiDataSource.getImageById(id).hits.map { it.toEntity() }


    override fun clearAllImages() {
        localDataSource.clearAllImages()
    }

    override suspend fun loadImages(): Flow<List<HitsItem>> = localDataSource.getAllImages().map {
        it.toEntity()
    }

    override fun loadImagesSize(): Int = localDataSource.loadImagesSize()


}