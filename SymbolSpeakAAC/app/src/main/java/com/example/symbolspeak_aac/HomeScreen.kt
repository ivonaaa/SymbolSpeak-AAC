package com.example.symbolspeak_aac

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.symbolspeak_aac.TextToSpeach.TextToSpeechViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    dataViewModel: DataViewModel = viewModel(),
    chosenSymbols: ChosenSymbols = ChosenSymbols(),
    textToSpeechViewModel: TextToSpeechViewModel = viewModel()
) {
    val products = dataViewModel.state.value.data

    Column {
        Row(
            modifier = Modifier
                .padding(5.dp)
        ) {
            Text(text = "search feature by symbol.title")
        }

        tts()

        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
        ) {
            LazyRow(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(0.8f)
            )
            {
                items(chosenSymbols.chosen) { chosen ->
                    SymbolView(product = chosen, chosenSymbols)
                }
            }
            Column() {
                Button(
                    onClick = { chosenSymbols.deleteLast() },
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(1f)
                        .fillMaxHeight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Toggle drawer"
                    )
                }
            }
        }
        products?.let {
            LazyVerticalGrid(
                cells = GridCells.Fixed(4),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(products) { product ->
                    SymbolView(product = product, chosenSymbols)
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

@Composable
fun tts(
    viewModel: TextToSpeechViewModel = viewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(20.dp),

    ){
        TextField(value = state.text, onValueChange = {
            viewModel.onTextFieldValueChange(it)
        })
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            viewModel.textToSpeech(context)
        }, enabled = state.isButtonEnabled
        ) {
            Text(text = "speak")
        }
    }
}
