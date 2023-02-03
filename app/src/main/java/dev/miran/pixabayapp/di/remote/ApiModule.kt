package dev.miran.pixabayapp.di.remote

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.miran.remote.api.ImagesApiService
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun provideApiService(@Named(DiNames.PIXABAY_API) retrofit: Retrofit): ImagesApiService =
        retrofit.create(ImagesApiService::class.java)


    @Provides
    @Singleton
    fun provideNoConnectionInterceptor(@ApplicationContext appContext: Context): NoConnectionInterceptor {
        return NoConnectionInterceptor(appContext)
    }
}
