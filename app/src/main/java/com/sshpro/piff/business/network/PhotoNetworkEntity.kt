package com.sshpro.piff.business.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class PhotoNetworkEntity(
    val title: String,
    @Json(name = "link")
    val url : String,
    @Json(name = "date_taken")
    val dateTaken: Date,
    @Json(name = "published")
    val datePublished: Date,
    val description: String,
    val author: String,
    @Json(name = "author_id")
    val authorId: String,
    val tags: String,
)