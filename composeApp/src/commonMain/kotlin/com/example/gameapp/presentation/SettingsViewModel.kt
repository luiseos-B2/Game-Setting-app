package com.example.gameapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameapp.domain.AudioEngine
import com.example.gameapp.domain.SettingsModel
import com.example.gameapp.domain.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: SettingsRepository,
    private val audioEngine: AudioEngine
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsModel())
    val uiState: StateFlow<SettingsModel> = _uiState.asStateFlow()

    init {
        observeSettings()
    }

    private fun observeSettings() {
        viewModelScope.launch {
            repository.getSettings().collectLatest { savedSettings ->
                _uiState.value = savedSettings
            }
        }
    }

    fun initialize() {
        audioEngine.initialize()
        val current = _uiState.value
        audioEngine.setMusicVolume(current.musicVolume)
        audioEngine.setSfxVolume(current.sfxVolume)
    }

    private fun updateAndSave(newSettings: SettingsModel) {
        _uiState.value = newSettings
        viewModelScope.launch {
            repository.saveSettings(newSettings)
        }
    }

    private fun updateSetting(update: SettingsModel.() -> SettingsModel): SettingsModel {
        val newSettings = _uiState.value.update()
        updateAndSave(newSettings)
        return newSettings
    }

    fun changeSfxVolume(volume: Float) {
        updateSetting { copy(sfxVolume = volume) }
        audioEngine.setSfxVolume(volume)
    }

    fun changeMusicVolume(volume: Float) {
        updateSetting { copy(musicVolume = volume) }
        audioEngine.setMusicVolume(volume)
    }

    fun nextLanguage() {
        updateSetting { copy(language = language.next()) }
    }

    fun previousLanguage() {
        updateSetting { copy(language = language.previous()) }
    }

    fun release() {
        audioEngine.release()
    }
}