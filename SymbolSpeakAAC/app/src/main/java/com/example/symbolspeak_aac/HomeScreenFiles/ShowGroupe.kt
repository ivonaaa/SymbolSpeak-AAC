package com.example.symbolspeak_aac.HomeScreenFiles

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import coil.compose.rememberAsyncImagePainter
import com.example.symbolspeak_aac.ChosenSymbolsFiles.ChosenSymbols
import com.example.symbolspeak_aac.Symbol.Symbol
import com.example.symbolspeak_aac.Symbol.SymbolView
import com.example.symbolspeak_aac.Symbol.colorPicker

@Composable
fun ShowGroup(
    type: String,
    symbols: List<Symbol>,
    chosenSymbols: ChosenSymbols,
    fontSize: Int
) {
    var show by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        border = BorderStroke(3.dp, color = colorPicker(type = type)),
    ) {
        Button(
            onClick = { show = true },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier.height(100.dp)
        ) {
            Group(type = type, image = symbols[0].imageURL)
            if (show) {
                Popup(
                    onDismissRequest = { show = false },
                    alignment = Alignment.TopCenter,
                    properties = PopupProperties()
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((LocalConfiguration.current.screenHeightDp - 220).dp),
                        border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                        shape = RoundedCornerShape(0.dp),
                        color = MaterialTheme.colors.background,
                    ) {
                        Column(
                            modifier = Modifier.padding(1.dp)
                        ) {
                            // Composable content to be shown in the Popup
                            Row {
                                TextButton(onClick = { show = false }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                    Text(text = "Back", fontSize = 20.sp)
                                }
                            }
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(text = type, fontSize = fontSize.sp, color = MaterialTheme.colors.primary)
                            }
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(3),
                                contentPadding = PaddingValues(8.dp)
                            ) {
                                items(symbols.size) {
                                    SymbolView(product = symbols[it], chosenSymbols)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Group(
    type: String,
    image: String
) {
    Column(
        modifier = Modifier.padding(1.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = type,
            textAlign = TextAlign.Center
        )
        Image(
            painter = rememberAsyncImagePainter(image),
            contentDescription = "product image",
            modifier = Modifier
                .padding(2.dp)
                .size(60.dp)
                .fillMaxWidth()
        )
    }
}