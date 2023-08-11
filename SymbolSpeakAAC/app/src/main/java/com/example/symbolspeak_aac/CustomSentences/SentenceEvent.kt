package com.example.symbolspeak_aac.CustomSentences

sealed interface SentenceEvent{
    object SaveSentence: SentenceEvent
    data class SetText(val text: String): SentenceEvent
    object ShowDialog: SentenceEvent
    object HideDialog: SentenceEvent
    data class DeleteSentence(val sentence: Sentence): SentenceEvent

}