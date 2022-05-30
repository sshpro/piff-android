package com.sshpro.piff.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sshpro.piff.business.domain.Photo

@ExperimentalFoundationApi
@Composable
fun PhotoGrid(
    photos: List<Photo>,
    onPhotoClick: (String) -> Unit = {}
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(10.dp),
        cells = GridCells.Adaptive(minSize = 200.dp)
    ) {
        items(photos) { photo ->
            PhotoItem(photo = photo, onPhotoClick = onPhotoClick)
        }
    }
}