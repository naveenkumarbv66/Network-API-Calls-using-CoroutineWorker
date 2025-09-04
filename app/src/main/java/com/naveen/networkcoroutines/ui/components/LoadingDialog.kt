package com.naveen.networkcoroutines.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingDialog(
    show: Boolean,
    title: String = "Loading",
    message: String = "Please wait..."
) {
    if (!show) return
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {},
        title = { Text(title) },
        text = {
            Column {
                Text(message)
                Spacer(modifier = Modifier.height(12.dp))
                CircularProgressIndicator()
            }
        }
    )
}


