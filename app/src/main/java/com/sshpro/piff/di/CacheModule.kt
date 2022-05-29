package com.sshpro.piff.di

import android.content.Context
import androidx.room.Room
import com.sshpro.piff.business.Mapper
import com.sshpro.piff.business.cache.*
import com.sshpro.piff.business.domain.Photo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Singleton
    @Provides
    fun provideCacheMapper(): Mapper<PhotoCacheEntity, Photo>{
        return CacheMapper()
    }

    @Singleton
    @Provides
    fun providePhotoDb(@ApplicationContext context: Context): PhotoDatabase {
        return Room
            .databaseBuilder(
                context,
                PhotoDatabase::class.java,
                PhotoDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePhotoDAO(photoDatabase: PhotoDatabase): PhotosDao {
        return photoDatabase.photoDao()
    }

    @Singleton
    @Provides
    fun provideCacheStrategy(): CacheStrategy {
        return DefaultCacheStrategy()
    }
}