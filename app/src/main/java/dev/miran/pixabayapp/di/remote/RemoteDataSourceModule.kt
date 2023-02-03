package dev.miran.pixabayapp.di.remote

import dev.miran.remote.apiDataSource.ImageDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.miran.repository.remote.dataSource.ImageRemoteDataSource

@InstallIn(SingletonComponent::class)
@Module
interface RemoteDataSourceModule {
    @Binds
    fun provide(imageDataSource: ImageDataSource): ImageRemoteDataSource
}
