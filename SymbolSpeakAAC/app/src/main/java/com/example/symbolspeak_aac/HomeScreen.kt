package com.example.symbolspeak_aac

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreen(
    dataViewModel: DataViewModel = viewModel()
) {
    val products = dataViewModel.state.value.data
    products?.let {
        LazyColumn {
            items(
                products
            ) { product ->
                Text(text = "Title: ")
                Text(text = product.title)
                Spacer(modifier = Modifier.padding(5.dp))
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