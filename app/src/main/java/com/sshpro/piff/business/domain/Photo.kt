package com.sshpro.piff.business.domain

import java.util.*

data class Photo(
    val url: String?,
    val title: String = "title",
    val dateTaken: Date = Date(),
    val datePublished: Date = Date(),
    val description: String = "",
    val author: String = "",
    val authorId: String = "",
    val tags: List<String> = listOf()
)