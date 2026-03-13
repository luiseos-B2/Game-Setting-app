package com.example.gameapp.domain

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getSettings(): Flow<SettingsModel>
    suspend fun saveSettings(settings: SettingsModel)
}