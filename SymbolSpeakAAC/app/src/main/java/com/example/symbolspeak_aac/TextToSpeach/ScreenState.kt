package com.example.symbolspeak_aac.TextToSpeach

import com.example.symbolspeak_aac.Symbol.Symbol

data class ScreenState(
    val isButtonEnabled: Boolean = true,
    val symbols: List<Symbol> = listOf()
)