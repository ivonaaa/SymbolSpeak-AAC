package com.example.symbolspeak_aac.TextToSpeach

import androidx.compose.runtime.mutableStateOf
import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.symbolspeak_aac.Symbol
import java.util.*

class TextToSpeechViewModel : ViewModel() {
    private val _state = mutableStateOf(ScreenState())
    val state: State<ScreenState> = _state
    private  var  textToSpeech:TextToSpeech? = null


    fun onTextFieldValueChange(symbols:kotlin.collections.List<Symbol>){
        var text = ""
        symbols.forEach {
            text += it.title
        }
        _state.value = state.value.copy(
            text = text
        )
    }

    fun textToSpeech(context: Context){
        _state.value = state.value.copy(
            isButtonEnabled = false
        )
        textToSpeech = TextToSpeech(
            context
        ) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.ENGLISH
                    txtToSpeech.setSpeechRate(1.0f)
                    txtToSpeech.speak(
                        _state.value.text,
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
}
