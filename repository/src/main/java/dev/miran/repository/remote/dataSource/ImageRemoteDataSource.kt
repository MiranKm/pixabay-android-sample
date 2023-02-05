package dev.miran.repository.remote.dataSource

import dev.miran.repository.remote.dto.ImageListResponseRemoteDto


interface ImageRemoteDataSource {
    suspend fun getImageByQuery(query:String, imageType:String?): ImageListResponseRemoteDto
    suspend fun getImageById(id:Int): ImageListResponseRemoteDto
}