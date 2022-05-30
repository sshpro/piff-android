package com.sshpro.piff.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sshpro.piff.business.DataState
import com.sshpro.piff.business.domain.Photo

@Composable
fun PhotoDetailView(dataState: DataState<Photo>) {
    when (dataState) {
        is DataState.Success<Photo> -> {
            PhotoDetail(photo = dataState.data)
        }
        is DataState.Error -> {
            ErrorView(dataState.exception.message)
        }
        is DataState.Loading -> {
            ProgressView()
        }
    }
}

@Composable
fun PhotoDetail(photo: Photo) {
    Column {
        AsyncImageView(
            url = photo.url,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
        )
        PhotoDetailRow("Title: ", photo.title)
        PhotoDetailRow("Author: ", photo.author)
        PhotoDetailRow("Author Id: ", photo.authorId)
        PhotoDetailRow("PhotoUrl: ", photo.url)
        PhotoDetailRow("Date Published: ", photo.datePublished.toString())
        PhotoDetailRow("Date Taken: ", photo.dateTaken.toString())
        PhotoDetailRow("Description: ", photo.description)
        PhotoDetailRow("Tags: ", photo.tags.joinToString(separator = ","))
    }
}

@Composable
fun PhotoDetailRow(key: String, value: String?) {
    if (value.isNullOrEmpty()) {
        return
    }
    Row {
        Text(
            text = key,
            modifier = Modifier
                .fillMaxWidth(0.25f)
                .wrapContentWidth(Alignment.Start),
            style = MaterialTheme.typography.h5
        )
        Text(
            text = value,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .align(Alignment.CenterVertically),
            style = MaterialTheme.typography.h6
        )
    }
}