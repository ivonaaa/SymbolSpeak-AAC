package com.example.symbolspeak_aac

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.symbolspeak_aac.ChosenSymbolsFiles.ChosenSymbolView
import com.example.symbolspeak_aac.ChosenSymbolsFiles.ChosenSymbols
import com.example.symbolspeak_aac.Firebase.DataViewModel
import com.example.symbolspeak_aac.History.History
import com.example.symbolspeak_aac.History.HistoryScreen
import com.example.symbolspeak_aac.HomeScreenFiles.ShowGroup
import com.example.symbolspeak_aac.InfoScreenFiles.WindowCenterOffsetPositionProvider
import com.example.symbolspeak_aac.SettingsScreenFiles.UserSettings
import com.example.symbolspeak_aac.Symbol.Symbol
import com.example.symbolspeak_aac.Symbol.SymbolView
import com.example.symbolspeak_aac.Symbol.colorPicker
import com.example.symbolspeak_aac.TextToSpeach.TextToSpeechViewModel


@Composable
fun HomeScreen(
    history: History,
    dataViewModel: DataViewModel = viewModel(),
    chosenSymbols: ChosenSymbols = ChosenSymbols(),
    ttsViewModel: TextToSpeechViewModel = viewModel()
) {
    val state = ttsViewModel.state.value
    val context = LocalContext.current
    val store = UserSettings(context)
    val ttsRate = store.getTtsRate.collectAsState(initial = 1.0)
    val products = dataViewModel.state.value.data
    val fontSize = store.getFontSize.collectAsState(initial = 16)

    var showHistory by remember {
        mutableStateOf(false)
    }

    Column {
        Button(
            onClick = { showHistory = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text("Show history", fontSize = 20.sp)
        }

        if (showHistory) {
            Popup(
                popupPositionProvider =
                WindowCenterOffsetPositionProvider(),
                onDismissRequest = { showHistory = false },
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(1f),
                    border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(0.dp),
                    color = MaterialTheme.colors.background,
                ) {
                    Column(
                        modifier = Modifier.padding(1.dp)
                    ) {
                        // Composable content to be shown in the Popup
                        Row {
                            TextButton(onClick = { showHistory = false }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back"
                                )
                                Text(text = "Back", fontSize = 20.sp)
                            }
                        }
                        HistoryScreen(history = history)
                    }
                }
            }
        }

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
                    if(chosenSymbols.chosen.isNotEmpty()) {
                        history.add(chosenSymbols.chosen)
                        ttsViewModel.onValueChange(chosenSymbols.chosen)
                        ttsViewModel.textToSpeech(
                            context,
                            ttsRate = ttsRate.value.toFloat(),
                            text = ""
                        )
                    }},
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
            val gruped = products.groupBy { it.type }
            val types = mutableStateListOf<String>()
            gruped.forEach {
                types.add(it.key)
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(gruped.size) {
                    val type = types[it]
                    val symbols = gruped.getValue(type)
                    ShowGroup(type = type, symbols = symbols, chosenSymbols = chosenSymbols, fontSize = fontSize.value)
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
