package com.example.symbolspeak_aac.ChosenSymbolsFiles

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.symbolspeak_aac.Symbol

class ChosenSymbols: ViewModel() {
    var chosen = mutableStateListOf<Symbol>()

    fun add(newSymbol: Symbol) {
        chosen.add(newSymbol)
    }

    fun deleteLast(){
        if(chosen.size > 0) {
            chosen.remove(chosen.last())
        }
    }

    fun delete(item: Symbol) {
        chosen.remove(item)
    }
}