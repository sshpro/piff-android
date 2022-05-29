package com.sshpro.piff.business.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "photos")
data class PhotoCacheEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "url")
    val url : String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "date_taken")
    val dateTaken: Date,

    @ColumnInfo(name = "date_published")
    val datePublished: Date,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "author_id")
    val authorId: String,

    @ColumnInfo(name = "tags")
    val tags: String
)