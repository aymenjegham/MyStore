package com.angelstudios.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorText(stringSource: Int) {
    Text(
        text = stringResource(id = stringSource),
        color = MaterialTheme.colorScheme.error,
        textAlign = TextAlign.Center,
    )
}