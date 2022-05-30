package com.sshpro.piff.business

import com.sshpro.piff.business.domain.Photo

interface Repository {
    suspend fun tryUpdateFeedCache()
    suspend fun get(): List<Photo>
    suspend fun get(url: String): Photo
}