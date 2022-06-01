package com.sshpro.piff.compose

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorView(message: String?, modifier: Modifier = Modifier) {
    Text(
        text = "Error $message",
        modifier = modifier,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h3
    )
}