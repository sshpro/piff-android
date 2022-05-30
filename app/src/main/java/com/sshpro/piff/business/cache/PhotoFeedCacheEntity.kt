package com.sshpro.piff.business.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sshpro.piff.business.network.PhotoNetworkEntity
import java.util.*

@Entity(tableName = "feed")
class PhotoFeedCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "dateModified")
    var dateModified: Date,

    @ColumnInfo(name = "generator")
    var generator: String,

    @ColumnInfo(name = "items")
    var items: List<PhotoNetworkEntity>
)