package dev.miran.pixabayapp.di.repository


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.miran.repository.ImageRepository
import dev.miran.repository.repositoryImpl.ImageRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {


    @Binds
    fun bindsLocalizationRepository(simRepositoryImpl: ImageRepositoryImpl): ImageRepository


}
