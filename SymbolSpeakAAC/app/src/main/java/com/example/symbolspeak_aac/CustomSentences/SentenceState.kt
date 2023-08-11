package com.example.symbolspeak_aac.CustomSentences

import com.example.symbolspeak_aac.CustomSentences.Sentence

data class SentenceState (
    val sentences: List<Sentence> = emptyList(),
    val text: String = "",
    val isAddingSentence: Boolean = false,
)