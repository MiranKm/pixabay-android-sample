package dev.miran.remote.apiDataSource

import dev.miran.remote.api.ImagesApiService
import dev.miran.repository.remote.dataSource.ImageRemoteDataSource
import dev.miran.repository.remote.dto.ImageResponseRemoteDto
import javax.inject.Inject


class ImageDataSource @Inject constructor(
    private val fastSimServices: ImagesApiService
) : ImageRemoteDataSource {

    override suspend fun getImageByQuery(query: String): ImageResponseRemoteDto =
        fastSimServices.getImages(query).body()!!

}
