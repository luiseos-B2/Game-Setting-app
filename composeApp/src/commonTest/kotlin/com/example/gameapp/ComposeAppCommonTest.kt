package com.example.gameapp

import com.example.gameapp.domain.Language
import kotlin.test.Test
import kotlin.test.assertEquals

class ComposeAppCommonTest {

    @Test
    fun nextLanguage_cyclesFromLastToFirst() {
        val next = Language.FRENCH.next()
        assertEquals(Language.ENGLISH, next)
    }

    @Test
    fun previousLanguage_cyclesFromFirstToLast() {
        val previous = Language.ENGLISH.previous()
        assertEquals(Language.FRENCH, previous)
    }
}