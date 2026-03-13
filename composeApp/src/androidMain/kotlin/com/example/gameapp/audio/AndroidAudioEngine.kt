package com.example.gameapp.audio

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.net.Uri
import com.example.gameapp.Res
import com.example.gameapp.domain.AudioEngine

class AndroidAudioEngine(private val context: Context) : AudioEngine {

    private var musicPlayer: MediaPlayer? = null
    private var sfxPlayer: MediaPlayer? = null

    private var musicVolume: Float = 1f
    private var sfxVolume: Float = 1f

    override fun initialize() {
        try {
            val musicUri = Res.getUri("files/test_music.mp3")
            val sfxUri = Res.getUri("files/test_sfx.mp3")
            val musicAssetPath = "composeResources/com.example.gameapp/files/test_music.mp3"
            val sfxAssetPath = "composeResources/com.example.gameapp/files/test_sfx.mp3"

            musicPlayer = createPlayer(musicAssetPath, musicUri)?.apply {
                isLooping = true
                setVolume(musicVolume, musicVolume)
                start()
            }

            sfxPlayer = createPlayer(sfxAssetPath, sfxUri)?.apply {
                setVolume(sfxVolume, sfxVolume)
                isLooping = true
                start()
            }

        } catch (e: Exception) {
            println("AudioEngine Android: Erro ao inicializar: ${e.message}")
        }
    }

    private fun createPlayer(assetPath: String, uri: String): MediaPlayer? {
        val fromAsset = createFromAsset(assetPath)
        if (fromAsset != null) return fromAsset

        return createFromUri(uri)
    }

    private fun createFromAsset(assetPath: String): MediaPlayer? {
        return try {
            val descriptor: AssetFileDescriptor = context.assets.openFd(assetPath)
            MediaPlayer().apply {
                setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
                prepare()
                descriptor.close()
            }
        } catch (e: Exception) {
            println("AudioEngine Android: Falha ao carregar asset $assetPath - ${e.message}")
            null
        }
    }

    private fun createFromUri(uri: String): MediaPlayer? {
        return try {
            MediaPlayer().apply {
                setDataSource(context, Uri.parse(uri))
                prepare()
            }
        } catch (e: Exception) {
            println("AudioEngine Android: Falha ao carregar URI $uri - ${e.message}")
            null
        }
    }

    override fun setMusicVolume(volume: Float) {
        musicVolume = volume
        musicPlayer?.setVolume(volume, volume)
    }

    override fun setSfxVolume(volume: Float) {
        sfxVolume = volume
        sfxPlayer?.setVolume(volume, volume)
    }

    override fun release() {
        musicPlayer?.stop()
        musicPlayer?.release()
        sfxPlayer?.stop()
        sfxPlayer?.release()
        musicPlayer = null
        sfxPlayer = null
    }
}