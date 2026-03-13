package com.example.gameapp.domain

data class SettingsModel(
    val sfxVolume: Float = 0.5f,
    val musicVolume: Float = 0.5f,
    val language: Language = Language.ENGLISH
)

enum class Language(val isoCode: String, val displayName: String) {
    ENGLISH("en", "ENGLISH"),
    PORTUGUESE("pt", "PORTUGUÊS"),
    SPANISH("es", "ESPAÑOL"),
    FRENCH("fr", "FRANÇAIS");

    fun next(): Language {
        val values = entries
        return values[(ordinal + 1) % values.size]
    }

    fun previous(): Language {
        val values = entries
        return if (ordinal == 0) values.last() else values[ordinal - 1]
    }
}