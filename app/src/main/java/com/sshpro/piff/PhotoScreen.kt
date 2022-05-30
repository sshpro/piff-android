package com.sshpro.piff

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoAlbum
import androidx.compose.ui.graphics.vector.ImageVector

enum class PhotoScreen(
    val icon: ImageVector,
) {
    Gallery(
        icon = Icons.Filled.PhotoAlbum,
    ),
    Detail(
        icon = Icons.Filled.Photo,
    );


    companion object {
        fun fromRoute(route: String?): PhotoScreen =
            when (route?.substringBefore("/")) {
                Gallery.name -> Gallery
                Detail.name -> Detail
                null -> Gallery
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}