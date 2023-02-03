package dev.miran.pixabayapp.di.repository

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryProviders {

/*

    @Provides
    @Singleton
    fun provideAuthRepository(dataSource: AuthorizationLocalDataSource): AuthorizationRepository =
        AuthorizationRepositoryImpl(dataSource)
*/

}