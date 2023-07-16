package com.example.symbolspeak_aac

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    dataViewModel: DataViewModel = viewModel(),
    chosenSymbols: ChosenSymbols = ChosenSymbols()
) {
    val products = dataViewModel.state.value.data

    Column {
        Row(modifier = Modifier 
            .padding(5.dp)
        ){
            Text(text = "search feature by symbol.title")
        }
        Row {
            LazyRow(
                modifier = Modifier
                    .padding(5.dp)
            )
            {
                items(chosenSymbols.chosen) { chosen ->
                    SymbolView(product = chosen, chosenSymbols)
                }
            }

            Button(onClick = { chosenSymbols.deleteLast()}) {
                Text(text = "Delete")
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
