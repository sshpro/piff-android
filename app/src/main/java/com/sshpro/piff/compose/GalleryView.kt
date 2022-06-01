package com.sshpro.piff.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sshpro.piff.R
import com.sshpro.piff.business.DataState
import com.sshpro.piff.business.domain.Photo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GalleryView(
    dataState: DataState<List<Photo>>,
    onPhotoClick: (String) -> Unit = {},
    onError: (message: String) -> Unit,
) {
    when (dataState) {
        is DataState.Success<List<Photo>> -> {
            PhotoGrid(photos = dataState.data, onPhotoClick = onPhotoClick)
        }
        is DataState.Error -> {
            onError(dataState.exception.message ?: stringResource(id = R.string.default_error))
        }
        is DataState.Loading -> {
            ProgressView()
        }
    }
}