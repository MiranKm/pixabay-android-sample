package dev.miran.remote.apiDataSource

import dev.miran.remote.api.ImagesApiService
import dev.miran.repository.remote.dataSource.ImageRemoteDataSource
import dev.miran.repository.remote.dto.ImageListResponseRemoteDto
import javax.inject.Inject


class ImageDataSource @Inject constructor(
    private val fastSimServices: ImagesApiService
) : ImageRemoteDataSource {

    override suspend fun getImageByQuery(query: String, imageType:String?): ImageListResponseRemoteDto =
        fastSimServices.getImageByQuery(query, imageType).body()!!

    override suspend fun getImageById(id: Int): ImageListResponseRemoteDto =
        fastSimServices.getImageById(id).body()!!

}
