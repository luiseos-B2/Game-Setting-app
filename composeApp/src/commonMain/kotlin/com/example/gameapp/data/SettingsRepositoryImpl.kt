package com.example.gameapp.data

import com.example.gameapp.domain.Language
import com.example.gameapp.domain.SettingsModel
import com.example.gameapp.domain.SettingsRepository
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsRepositoryImpl(
    private val settings: Settings = Settings()
) : SettingsRepository {

    private val _gameSettings = MutableStateFlow(loadSettings())

    override fun getSettings(): Flow<SettingsModel> = _gameSettings

    override suspend fun saveSettings(settings: SettingsModel) {
        this@SettingsRepositoryImpl.settings.putFloat(KEY_SFX_VOLUME, settings.sfxVolume)
        this@SettingsRepositoryImpl.settings.putFloat(KEY_MUSIC_VOLUME, settings.musicVolume)
        this@SettingsRepositoryImpl.settings.putString(KEY_LANGUAGE, settings.language.name)
        _gameSettings.value = settings
    }

    private fun loadSettings(): SettingsModel {
        return SettingsModel(
            sfxVolume = settings.getFloat(KEY_SFX_VOLUME, DEFAULT_VOLUME),
            musicVolume = settings.getFloat(KEY_MUSIC_VOLUME, DEFAULT_VOLUME),
            language = settings.getString(KEY_LANGUAGE, Language.ENGLISH.name).toLanguage()
        )
    }

    private fun String.toLanguage(): Language =
        runCatching { Language.valueOf(this) }.getOrDefault(Language.ENGLISH)

    private companion object {
        const val KEY_SFX_VOLUME = "sfx_vol"
        const val KEY_MUSIC_VOLUME = "music_vol"
        const val KEY_LANGUAGE = "lang"
        const val DEFAULT_VOLUME = 0.5f
    }
}