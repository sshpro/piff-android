package com.sshpro.piff.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.sshpro.piff.R

@Composable
fun DefaultSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier,
    onDismiss: () -> Unit
) {
    SnackbarHost(
        modifier = modifier,
        hostState = snackbarHostState,
        snackbar = { data ->
            SnackBar(data = data, onDismiss)
        }
    )
}

@Composable
fun SnackBar(data: SnackbarData, onDismiss: () -> Unit) {
    Snackbar(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_snackbar)),
        content = {
            Text(
                text = data.message,
                style = MaterialTheme.typography.body2,
                color = Color.White
            )
        },
        action = {
            data.actionLabel?.let { actionLabel ->
                TextButton(onClick = { onDismiss() }) {
                    Text(
                        text = actionLabel,
                        style = MaterialTheme.typography.body2,
                        color = Color.White
                    )
                }
            }
        }
    )
}