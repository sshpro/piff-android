package com.sshpro.piff.compose

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ShareCompat
import com.sshpro.piff.R
import com.sshpro.piff.business.DataState
import com.sshpro.piff.business.domain.Photo

@Composable
fun PhotoDetailView(dataState: DataState<Photo>, onError: (message: String) -> Unit) {
    when (dataState) {
        is DataState.Success<Photo> -> {
            PhotoDetail(photo = dataState.data)
        }
        is DataState.Error -> {
            ErrorView(
                message = dataState.exception.message,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
            onError(dataState.exception.message ?: stringResource(id = R.string.default_error))
        }
        is DataState.Loading -> {
            ProgressView()
        }
    }
}

@Composable
fun PhotoDetail(photo: Photo) {
    Column {
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .padding(0.dp)
        ) {
            AsyncImageView(
                url = photo.url,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
            ShareButton(photo)
        }
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
private fun ShareButton(photo: Photo) {
    val context = LocalContext.current
    IconButton(
        onClick = { startShareIntent(context, photo) },
        content = { Icon(imageVector = Icons.Filled.Share, contentDescription = "") },
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_default))
    )
}

private fun startShareIntent(context: Context, photo: Photo) {
    ShareCompat.IntentBuilder(context)
        .setType("text/plain")
        .setChooserTitle(photo.title)
        .setText(photo.url)
        .startChooser()
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