package com.example.gameapp.domain

interface AudioEngine {
    fun setMusicVolume(volume: Float)
    fun setSfxVolume(volume: Float)
    fun initialize()
    fun release()
}