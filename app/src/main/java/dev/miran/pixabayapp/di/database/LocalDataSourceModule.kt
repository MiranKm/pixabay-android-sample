package dev.miran.pixabayapp.di.database

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.miran.local.databaseDataSource.ImagesDatabaseDataSource
import dev.miran.repository.local.dataSource.ImageLocalDataSource

@InstallIn(SingletonComponent::class)
@Module
interface LocalDataSourceModule {

    @Binds
    fun provideImagesLocalDataSource(dataSource: ImagesDatabaseDataSource): ImageLocalDataSource

}
