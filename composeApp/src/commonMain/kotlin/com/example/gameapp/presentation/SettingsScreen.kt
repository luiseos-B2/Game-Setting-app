package com.example.gameapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel
) {
    var isSettingsVisible by remember { mutableStateOf(false) }

    DisposableEffect(viewModel) {
        viewModel.initialize()
        onDispose { viewModel.release() }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { isSettingsVisible = true }) {
            Text("OPEN SETTINGS")
        }

        if (isSettingsVisible) {
            SettingsModal(
                viewModel = viewModel,
                onDismiss = { isSettingsVisible = false }
            )
        }
    }
}

