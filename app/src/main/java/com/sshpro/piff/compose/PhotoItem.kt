package com.sshpro.piff.compose

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.sshpro.piff.R
import com.sshpro.piff.business.domain.Photo

@Composable
fun PhotoItem(photo: Photo, onPhotoClick: (String) -> Unit = {}) {
    Card(
        elevation = dimensionResource(id = R.dimen.card_elevation),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
            .padding(bottom = dimensionResource(id = R.dimen.card_bottom_margin))
            .clickable {
                onPhotoClick(photo.url ?: "")
            }
    ) {
        Column(Modifier.fillMaxWidth()) {
            AsyncImageView(
                url = photo.url, modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.photo_height))
            )
            Text(
                text = photo.title,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.margin_default))
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun AsyncImageView(url: String?, modifier: Modifier) {
    url?.let {
        val image = loadPhoto(
            url = url, defaultImageRes = R.drawable
                .ic_launcher_background
        ).value
        image?.let { img ->
            Image(
                bitmap = img.asImageBitmap(),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.accessibility_photo),
                modifier = modifier
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun loadPhoto(
    url: String,
    @DrawableRes defaultImageRes: Int
): MutableState<Bitmap?> {
    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(defaultImageRes)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }

        })

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }

        })
    return bitmapState
}
