package com.example.symbolspeak_aac.History

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.symbolspeak_aac.SettingsScreenFiles.UserSettings
import com.example.symbolspeak_aac.Symbol.Symbol
import com.example.symbolspeak_aac.Symbol.SymbolLayout
import com.example.symbolspeak_aac.Symbol.colorPicker
import com.example.symbolspeak_aac.TextToSpeach.TextToSpeechViewModel

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