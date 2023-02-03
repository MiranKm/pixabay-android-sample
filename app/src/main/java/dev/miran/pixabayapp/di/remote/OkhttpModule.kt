package dev.miran.pixabayapp.di.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.miran.pixabayapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module

object OkhttpModule {
    private const val LOGGING_INTERCEPTOR_TAG = "okhttp"
    private const val DEFAULT_TIMEOUT = 30L
    private const val CONNECT_TIMEOUT = DEFAULT_TIMEOUT
    private const val READ_TIMEOUT = DEFAULT_TIMEOUT
    private const val WRITE_TIMEOUT = DEFAULT_TIMEOUT

    @Provides
    @Named(DiNames.PIXABAY_API)
    fun provideTMDBApiPath(): String = DiNames.PIXABAY_API

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor {
        Timber.tag(LOGGING_INTERCEPTOR_TAG).i(it)
    }.setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    @Named(DiNames.PIXABAY_API)
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        noConnectionInterceptor: NoConnectionInterceptor
    ): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(noConnectionInterceptor)
        .addInterceptor(HeaderInterceptor())
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .build()

}


class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Device-Platform", "android")
                .addHeader("Accept", "application/json")
                .addHeader("debug", BuildConfig.DEBUG.toString())
                .build()
        )
    }
}
