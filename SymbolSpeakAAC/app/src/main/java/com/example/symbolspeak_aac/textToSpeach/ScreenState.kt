package com.example.symbolspeak_aac.textToSpeach

import com.example.symbolspeak_aac.symbol.Symbol

data class ScreenState(
    val isButtonEnabled: Boolean = true,
    val symbols: List<Symbol> = listOf()
)