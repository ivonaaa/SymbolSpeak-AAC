package com.example.symbolspeak_aac.TextToSpeach

import androidx.compose.runtime.mutableStateOf
import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.symbolspeak_aac.Symbol.Symbol
import java.util.*

class TextToSpeechViewModel : ViewModel() {
    private val _state = mutableStateOf(ScreenState())
    val state: State<ScreenState> = _state
    private  var  textToSpeech: TextToSpeech? = null


    fun onValueChange(symbols: List<Symbol>){
        _state.value = state.value.copy(
            symbols = symbols
        )
    }

    fun textToSpeech(context: Context, ttsRate: Float) {
        _state.value = state.value.copy(
            isButtonEnabled = false
        )

        val text = transformSymbolsToText()

        textToSpeech = TextToSpeech(
            context
        ) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.ENGLISH
                    txtToSpeech.setSpeechRate(ttsRate)
                    txtToSpeech.speak(
                        text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }

            _state.value = state.value.copy(
                isButtonEnabled = true
            )
        }
    }

    private fun transformSymbolsToText(): String {
        var text = ""
        _state.value.symbols.forEach {
            text += it.title + " "
        }
        return text
    }
}
