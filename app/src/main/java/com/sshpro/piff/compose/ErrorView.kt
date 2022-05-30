package com.sshpro.piff.compose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorView(message: String?) {
    Text(text = "Error $message")
}