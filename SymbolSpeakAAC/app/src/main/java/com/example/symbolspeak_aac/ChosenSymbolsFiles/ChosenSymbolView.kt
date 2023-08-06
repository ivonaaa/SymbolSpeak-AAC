package com.example.symbolspeak_aac

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.symbolspeak_aac.ChosenSymbolsFiles.ChosenSymbols
import com.example.symbolspeak_aac.Symbol.Symbol

@Composable
fun ChosenSymbolView(
    product : Symbol,
    chosenSymbols: ChosenSymbols
) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        border = BorderStroke(2.dp, Color.White),
    ) {
        Button(onClick = { chosenSymbols.delete(product) },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier.height(100.dp)
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
                        .padding(2.dp)
                        .size(60.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}