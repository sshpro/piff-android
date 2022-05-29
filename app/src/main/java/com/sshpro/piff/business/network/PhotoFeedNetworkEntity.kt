package com.sshpro.piff.business.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class PhotoFeedNetworkEntity(
    val title: String,
    @Json(name = "link")
    val url: String,
    val description: String = "",
    @Json(name = "modified")
    val dateModified: Date,
    val generator: String,
    val items: List<PhotoNetworkEntity>
)

