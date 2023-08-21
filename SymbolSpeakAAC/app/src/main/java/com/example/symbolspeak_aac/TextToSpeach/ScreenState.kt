package com.example.symbolspeak_aac.TextToSpeach

import com.example.symbolspeak_aac.Symbol.Symbol

data class ScreenState(
    var isButtonEnabled: Boolean = true,
    val symbols: List<Symbol> = listOf()
)