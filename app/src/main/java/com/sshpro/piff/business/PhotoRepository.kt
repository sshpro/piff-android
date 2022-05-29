package com.sshpro.piff.business

import com.sshpro.piff.business.cache.CacheMapper
import com.sshpro.piff.business.cache.CacheStrategy
import com.sshpro.piff.business.cache.PhotosDao
import com.sshpro.piff.business.domain.Photo
import com.sshpro.piff.business.network.NetworkMapper
import com.sshpro.piff.business.network.NetworkService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val networkService: NetworkService,
    private val photoDao: PhotosDao,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper,
    private val cacheStrategy: CacheStrategy,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
) : Repository {

    override val photosFlow: Flow<DataState<List<Photo>>>
        get() = flow {
            emit(DataState.Loading)
            val photos = fetchPhotos()
            emit(DataState.Success(photos))
        }.flowOn(coroutineDispatcher)

    override suspend fun tryUpdateFeedCache() {
        if (cacheStrategy.shouldUpdate()) fetchPhotos()
    }

    private suspend fun fetchPhotos(): List<Photo> {
        val photos = networkMapper.mapToDomainList(networkService.getPhotoFeed().items)
        //TODO cache
        return photos
    }
}