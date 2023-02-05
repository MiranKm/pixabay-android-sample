package dev.miran.local.dao

import androidx.room.*
import dev.miran.repository.local.dto.HitsItemLocalDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {

    @Query("SELECT * FROM image_hits")
    fun getAllImages(): Flow<List<HitsItemLocalDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<HitsItemLocalDto>)


    @Query("SELECT COUNT(*) FROM image_hits")
    fun hitImagesSize(): Int

    @Query("delete from image_hits")
    fun clearAllImages()

}