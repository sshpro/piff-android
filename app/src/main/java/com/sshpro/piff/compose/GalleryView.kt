package com.sshpro.piff.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.sshpro.piff.business.DataState
import com.sshpro.piff.business.domain.Photo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GalleryView(
    dataState: DataState<List<Photo>>,
    onPhotoClick: (String) -> Unit = {},
) {
    when (dataState) {
        is DataState.Success<List<Photo>> -> {
            PhotoGrid(photos = dataState.data, onPhotoClick = onPhotoClick)
        }
        is DataState.Error -> {
            ErrorView(dataState.exception.message)
        }
        is DataState.Loading -> {
            ProgressView()
        }
    }
}