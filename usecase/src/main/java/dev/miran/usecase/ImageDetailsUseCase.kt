package dev.miran.usecase

import dev.miran.repository.ImageRepository
import javax.inject.Inject


class ImageDetailsUseCase @Inject constructor(
    private val repository: ImageRepository
) {

    suspend fun getImageById(id: Int) = repository.getImageById(id)

}