package com.example.symbolspeak_aac

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.symbolspeak_aac.TextToSpeach.textToSpeechView


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    dataViewModel: DataViewModel = viewModel(),
    chosenSymbols: ChosenSymbols = ChosenSymbols()
) {
    val products = dataViewModel.state.value.data

    Column {
        textToSpeechView()

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
                    ChosenSymbolView(product = chosen, chosenSymbols)
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
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(products.size, itemContent = {
                    SymbolView(product = products[it], chosenSymbols)
                })
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
