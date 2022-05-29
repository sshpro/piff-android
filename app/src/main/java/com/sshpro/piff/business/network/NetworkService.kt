package com.sshpro.piff.business.network

interface NetworkService {
    suspend fun getPhotoFeed(): PhotoFeedNetworkEntity
}