package com.example.symbolspeak_aac

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.symbolspeak_aac.ChosenSymbolsFiles.ChosenSymbolView
import com.example.symbolspeak_aac.ChosenSymbolsFiles.ChosenSymbols
import com.example.symbolspeak_aac.Firebase.DataViewModel
import com.example.symbolspeak_aac.SettingsScreenFiles.UserSettings
import com.example.symbolspeak_aac.Symbol.SymbolView
import com.example.symbolspeak_aac.TextToSpeach.TextToSpeechViewModel


@Composable
fun HomeScreen(
    dataViewModel: DataViewModel = viewModel(),
    chosenSymbols: ChosenSymbols = ChosenSymbols(),
    ttsViewModel: TextToSpeechViewModel = viewModel()
) {
    val state = ttsViewModel.state.value
    val context = LocalContext.current
    val store = UserSettings(context)
    val ttsRate = store.getTtsRate.collectAsState(initial = 1.0)
    val products = dataViewModel.state.value.data

    Column {

        
        Row(
            modifier = Modifier
                .padding(5.dp)
                .height(100.dp)
                .fillMaxWidth()
                .border(width = 2.dp, Color.White)
        ) {
            LazyRow(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(0.8f)
            )
            {
                itemsIndexed(chosenSymbols.chosen) { index, chosen ->
                    ChosenSymbolView(product = chosen, index = index, chosenSymbols)
                }
            }
            Column {
                Button(
                    onClick = { chosenSymbols.deleteLast() },
                    modifier = Modifier
                        .padding(5.dp)
                        .height(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete last symbol"
                    )
                }

                Button(onClick = {
                    ttsViewModel.onValueChange(chosenSymbols.chosen)
                    ttsViewModel.textToSpeech(context, ttsRate = ttsRate.value.toFloat(), text = "")
                },
                    enabled = state.isButtonEnabled,
                    modifier = Modifier
                        .padding(5.dp)
                        .height(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play sentence"
                    )
                }
            }
        }

        products?.let {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(products.size) {
                    SymbolView(product = products[it], chosenSymbols)
                }
            }
        }

        val e = dataViewModel.state.value.e
        e?.let {
            Text(
                text = e.message!!
            )
        }
    }
}
