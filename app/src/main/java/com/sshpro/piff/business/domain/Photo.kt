package com.sshpro.piff.business.domain

import java.util.*

data class Photo(
    val url: String,
    val title: String,
    val dateTaken: Date,
    val datePublished: Date,
    val description: String,
    val author: String,
    val authorId: String,
    val tags: List<String>
)