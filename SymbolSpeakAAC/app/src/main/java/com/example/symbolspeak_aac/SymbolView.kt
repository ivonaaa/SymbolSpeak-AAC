package com.example.symbolspeak_aac

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.symbolspeak_aac.ChosenSymbolsFiles.ChosenSymbols

@Composable
fun SymbolView(
    product : Symbol,
    chosenSymbols: ChosenSymbols
) {
    Card(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        border = BorderStroke(3.dp, color = colorPicker(type = product.type)),
    ) {
        Button(onClick = { chosenSymbols.add(product) },
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
                    contentDescription = "product image",
                    modifier = Modifier
                        .padding(2.dp)
                        .size(60.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

fun colorPicker(type : String): Color {
    return when (type) {
        "Ayes/no" -> Color.Cyan
        "fruit" -> Color.Green
        "vegetable" -> Color.Yellow
        "math" -> Color.Blue
        "feelings" -> Color.Magenta
        else -> Color.Gray
    }
}