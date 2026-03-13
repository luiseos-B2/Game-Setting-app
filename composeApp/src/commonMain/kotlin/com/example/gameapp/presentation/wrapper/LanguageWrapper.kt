package com.example.gameapp.presentation.wrapper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import com.example.gameapp.presentation.SettingsViewModel
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.LanguageQualifier

val LocalAppLanguage = compositionLocalOf { "en" }

@OptIn(InternalResourceApi::class)
val LanguageQualifier.current: String
    @Composable
    get() = LocalAppLanguage.current

@OptIn(InternalResourceApi::class)
@Composable
fun LanguageWrapper(
    viewModel: SettingsViewModel,
    content: @Composable () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    CompositionLocalProvider(LocalAppLanguage provides state.language.isoCode) {
        content()
    }
}