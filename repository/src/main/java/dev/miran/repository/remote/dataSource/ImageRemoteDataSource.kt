package dev.miran.repository.remote.dataSource

import dev.miran.repository.remote.dto.ImageResponseRemoteDto


interface ImageRemoteDataSource {
    suspend fun getImageByQuery(query:String): ImageResponseRemoteDto
}