package com.example.symbolspeak_aac

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ChosenSymbols: ViewModel() {
    public var chosen = mutableStateListOf<Symbol>()

    fun add(newSymbol: Symbol) {
        chosen.add(newSymbol)
    }

    fun deleteLast(){
        chosen.remove(chosen.last())
    }
}