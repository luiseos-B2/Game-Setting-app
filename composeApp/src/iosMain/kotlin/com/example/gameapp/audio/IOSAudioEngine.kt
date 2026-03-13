package com.example.gameapp.audio

import com.example.gameapp.Res
import com.example.gameapp.domain.AudioEngine
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFAudio.AVAudioPlayer
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.setActive
import platform.Foundation.NSURL

class IOSAudioEngine : AudioEngine {

    private var musicPlayer: AVAudioPlayer? = null
    private var sfxPlayer: AVAudioPlayer? = null

    private var musicVolume: Float = 1f
    private var sfxVolume: Float = 1f

    @OptIn(ExperimentalForeignApi::class)
    override fun initialize() {
        try {
            val session = AVAudioSession.sharedInstance()
            session.setCategory(AVAudioSessionCategoryPlayback, error = null)
            session.setActive(true, error = null)

            val musicUri = Res.getUri("files/test_music.mp3")
            val sfxUri = Res.getUri("files/test_sfx.mp3")

            musicPlayer = createPlayer(musicUri)?.apply {
                numberOfLoops = -1
                volume = musicVolume
                prepareToPlay()
                play()
            }

            sfxPlayer = createPlayer(sfxUri)?.apply {
                volume = sfxVolume
                numberOfLoops = -1
                prepareToPlay()
                play()
            }

        } catch (e: Exception) {
            println("AudioEngine iOS: Erro ao inicializar: ${e.message}")
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun createPlayer(uri: String): AVAudioPlayer? {
        val url = NSURL.URLWithString(uri)
        return if (url != null) {
            AVAudioPlayer(contentsOfURL = url, error = null)
        } else {
            println("AudioEngine iOS: URI invalida para audio: $uri")
            null
        }
    }

    override fun setMusicVolume(volume: Float) {
        musicVolume = volume
        musicPlayer?.volume = volume
    }

    override fun setSfxVolume(volume: Float) {
        sfxVolume = volume
        sfxPlayer?.volume = volume
    }

    override fun release() {
        musicPlayer?.stop()
        sfxPlayer?.stop()
        musicPlayer = null
        sfxPlayer = null
    }
}