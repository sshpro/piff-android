package com.sshpro.piff.business.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PhotoCacheEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotosDao

    companion object {
        const val DATABASE_NAME: String = "photos_db"
    }
}