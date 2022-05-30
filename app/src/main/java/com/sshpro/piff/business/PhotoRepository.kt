package com.sshpro.piff.business

import com.sshpro.piff.business.cache.CacheMapper
import com.sshpro.piff.business.cache.CacheStrategy
import com.sshpro.piff.business.cache.PhotosDao
import com.sshpro.piff.business.domain.Photo
import com.sshpro.piff.business.network.NetworkMapper
import com.sshpro.piff.business.network.NetworkService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val networkService: NetworkService,
    private val photoDao: PhotosDao,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper,
    private val cacheStrategy: CacheStrategy,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
) : Repository {

    override suspend fun tryUpdateFeedCache() {
        if (cacheStrategy.shouldUpdate()) get()
    }

    private var _photos: List<Photo> = listOf()
    override suspend fun get(): List<Photo> {
        if (_photos.isNotEmpty()) {
            return _photos
        }
        _photos = networkMapper.mapToDomainList(networkService.getPhotoFeed().items)
        return _photos
    }

    override suspend fun get(url: String): Photo {
        return get().first { it.url == url }
    }
}
