package com.example.symbolspeak_aac

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    dataViewModel: DataViewModel = viewModel()
) {
    val products = dataViewModel.state.value.data

    products?.let {
        LazyVerticalGrid(
            cells = GridCells.Fixed(4),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(products) { product ->
                Card(
                    modifier = Modifier.padding(4.dp)
                        .fillMaxWidth(),
                    border = BorderStroke(2.dp, Color.LightGray),
                ) {
                    Column(
                        modifier = Modifier.padding(1.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = product.title
                        )
                        Image(
                            painter = rememberAsyncImagePainter(product.imageURL),
                            contentDescription = "gfg image",
                            modifier = Modifier
                                .size(60.dp)
                                .fillMaxWidth()
                        )
                    }
                }
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
