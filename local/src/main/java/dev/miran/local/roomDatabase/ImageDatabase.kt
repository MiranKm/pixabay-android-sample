package dev.miran.local.roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.miran.local.dao.ImagesDao
import dev.miran.repository.local.dto.HitsItemLocalDto

@Database(entities = [HitsItemLocalDto::class], version = 1)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imagesDao(): ImagesDao
}