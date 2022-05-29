package com.sshpro.piff.business.network

import javax.inject.Inject

class NetworkServiceImpl @Inject constructor(private val photoFeedService: PhotoFeedService) :
    NetworkService {
    override suspend fun getPhotoFeed(): PhotoFeedNetworkEntity {
        return photoFeedService.get()
    }
}