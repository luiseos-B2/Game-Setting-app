package com.example.gameapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gameapp.audio.AndroidAudioEngine
import com.example.gameapp.data.SettingsRepositoryImpl
import com.example.gameapp.presentation.SettingsScreen
import com.example.gameapp.presentation.SettingsViewModel
import com.example.gameapp.presentation.wrapper.LanguageWrapper

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        val audioEngine = AndroidAudioEngine(this)
        val repository = SettingsRepositoryImpl()

        viewModel = SettingsViewModel(repository, audioEngine)

        setContent {
            LanguageWrapper(viewModel) {
                SettingsScreen(viewModel)
            }
        }
    }
}


@Preview
@Composable
fun AppAndroidPreview() {
//    App()
}