package com.example.symbolspeak_aac.History

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.symbolspeak_aac.SettingsScreenFiles.UserSettings
import com.example.symbolspeak_aac.Symbol.Symbol
import com.example.symbolspeak_aac.Symbol.SymbolLayout
import com.example.symbolspeak_aac.Symbol.colorPicker
import com.example.symbolspeak_aac.TextToSpeach.TextToSpeechViewModel

data class HistoryData(
    val symbols: List<Symbol>
)

class History : ViewModel() {
    var history = mutableStateListOf<HistoryData>()

    fun add(symbols: List<Symbol>){
        if (history.size == 5){
            history.removeAt(0)
        }
        history.add(HistoryData(symbols))
    }
}