package com.sshpro.piff.business

import com.sshpro.piff.business.domain.Photo
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun tryUpdateFeedCache()
    val photosFlow: Flow<DataState<List<Photo>>>
}