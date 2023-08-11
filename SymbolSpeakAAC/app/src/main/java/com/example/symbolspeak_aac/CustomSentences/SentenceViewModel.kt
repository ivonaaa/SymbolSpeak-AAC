package com.example.symbolspeak_aac.CustomSentences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SentenceViewModel(
    private val dao: SentenceDao
): ViewModel() {
    private val _sentences = dao.getSentences()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(SentenceState())
    val state = combine(_state, _sentences) { state, sentences ->
        state.copy(
            sentences = sentences
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SentenceState())

    fun onEvent(event: SentenceEvent) {
        when(event){
            is SentenceEvent.DeleteSentence -> {
                viewModelScope.launch {
                    dao.deleteSentence(event.sentence)
                }
            }
            SentenceEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingSentence = false
                ) }
            }
            SentenceEvent.SaveSentence -> {
                val text = state.value.text

                if (text.isBlank()) {
                    return
                }

                val sentence = Sentence(
                    text = text
                )
                viewModelScope.launch {
                    dao.insertSentence(sentence)
                }
                _state.update { it.copy(
                    isAddingSentence = false,
                    text = ""
                ) }
            }
            is SentenceEvent.SetText -> {
                _state.update { it.copy(
                    text = event.text
                ) }
            }
            SentenceEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingSentence = true
                ) }
            }
        }
    }
}