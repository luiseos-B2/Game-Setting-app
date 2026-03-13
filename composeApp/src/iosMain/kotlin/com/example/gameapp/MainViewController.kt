package com.example.gameapp

import androidx.compose.ui.window.ComposeUIViewController
import com.example.gameapp.audio.IOSAudioEngine
import com.example.gameapp.data.SettingsRepositoryImpl
import com.example.gameapp.presentation.SettingsScreen
import com.example.gameapp.presentation.SettingsViewModel
import com.example.gameapp.presentation.wrapper.LanguageWrapper
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {

    val audioEngine = IOSAudioEngine()
    val repository = SettingsRepositoryImpl()
    val viewModel = SettingsViewModel(repository, audioEngine)

    return ComposeUIViewController {
        LanguageWrapper(viewModel) {
            SettingsScreen(viewModel)
        }
    }
}