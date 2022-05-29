package com.sshpro.piff.business.network

import retrofit2.http.GET
import retrofit2.http.Query

const val QUERY_FORMAT = "format"
const val DEFAULT_FORMAT = "json"
const val QUERY_NO_JSON_CALLBACK = "nojsoncallback"
const val DEFAULT_NO_JSON_CALLBACK = 1


interface PhotoFeedService {

    @GET("feeds/photos_public.gne")
    suspend fun get(
        @Query(QUERY_FORMAT) format: String = DEFAULT_FORMAT,
        @Query(QUERY_NO_JSON_CALLBACK) noJsonCallback: Int = DEFAULT_NO_JSON_CALLBACK
    ): PhotoFeedNetworkEntity
}