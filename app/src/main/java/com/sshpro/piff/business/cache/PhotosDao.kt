package com.sshpro.piff.business.cache

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PhotosDao {
    @Query("SELECT * FROM photos ORDER BY date_published")
    suspend fun get(): List<PhotoCacheEntity>
}