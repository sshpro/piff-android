package com.sshpro.piff.compose

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sshpro.piff.PhotoScreen

@Composable
fun PhotoTopView(
    currentScreen: PhotoScreen
) {
    TopAppBar(
        title = {
            Text(text = currentScreen.name)
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(currentScreen.icon, "")
            }
        },
        backgroundColor = Color.Blue,
        contentColor = Color.White,
        elevation = 12.dp
    )
}