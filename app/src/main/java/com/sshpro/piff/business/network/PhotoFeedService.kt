package com.sshpro.piff.business.network

import retrofit2.http.GET
import retrofit2.http.Query

const val QUERY_FORMAT = "format"
const val DEFAULT_FORMAT = "json"

interface PhotoFeedService {

    @GET
    suspend fun get(@Query(QUERY_FORMAT) format: String = DEFAULT_FORMAT): PhotoFeedNetworkEntity
}