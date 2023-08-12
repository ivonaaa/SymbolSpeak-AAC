package com.example.symbolspeak_aac.History

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.symbolspeak_aac.ChosenSymbolsFiles.ChosenSymbols
import com.example.symbolspeak_aac.SettingsScreenFiles.UserSettings
import com.example.symbolspeak_aac.Symbol.Symbol
import com.example.symbolspeak_aac.Symbol.SymbolLayout
import com.example.symbolspeak_aac.Symbol.SymbolView
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

@Composable
fun HistoryScreen(
    history: History,
    ttsViewModel: TextToSpeechViewModel = viewModel()
) {
    val context = LocalContext.current
    val store = UserSettings(context)
    val ttsRate = store.getTtsRate.collectAsState(initial = 1.0)
    Column(
    ) {
        history.history.forEach { products ->
            Button(onClick = {
                    ttsViewModel.onValueChange(products.symbols)
                    ttsViewModel.textToSpeech(context, ttsRate = ttsRate.value.toFloat(), text = "")
                },
                modifier = Modifier
                    .padding(16.dp)
                    .background(MaterialTheme.colors.secondary)
            ) {
                GridSymbolDisplay(symbols = products.symbols)
            }
        }
    }
}

@Composable
fun GridSymbolDisplay(
    symbols: List<Symbol>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(2.dp)
    ) {
        items(symbols.size) {
            Card(
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth(),
                border = BorderStroke(3.dp, color = colorPicker(type = symbols[it].type)),
            ) {
                SymbolLayout(product = symbols[it])
            }
        }
    }
}