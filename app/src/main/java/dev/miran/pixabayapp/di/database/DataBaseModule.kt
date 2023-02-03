package dev.miran.pixabayapp.di.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.miran.local.dao.ImagesDao
import dev.miran.local.roomDatabase.ImageDatabase
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {
    private const val DATA_BASE_NAME = "pixabay_db"

    @Singleton
    @Provides
    fun provideAppDataBase(
        @ApplicationContext applicationContext: Context
    ): ImageDatabase = Room.databaseBuilder(
        applicationContext,
        ImageDatabase::class.java,
        DATA_BASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideImagesDao(database: ImageDatabase): ImagesDao = database.imagesDao()


}
