package com.sshpro.piff.di

import com.sshpro.piff.business.PhotoRepository
import com.sshpro.piff.business.Repository
import com.sshpro.piff.business.cache.CacheMapper
import com.sshpro.piff.business.cache.CacheStrategy
import com.sshpro.piff.business.cache.PhotosDao
import com.sshpro.piff.business.network.NetworkMapper
import com.sshpro.piff.business.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(
        networkService: NetworkService,
        photoDao: PhotosDao,
        cacheStrategy: CacheStrategy,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper,
        coroutineDispatcher: CoroutineDispatcher
    ): Repository {
        return PhotoRepository(
            networkService = networkService,
            photoDao = photoDao,
            cacheStrategy = cacheStrategy,
            networkMapper = networkMapper,
            cacheMapper = cacheMapper,
            coroutineDispatcher = coroutineDispatcher
        )
    }
}