package com.example.symbolspeak_aac.TextToSpeach

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun textToSpeechView(
    viewModel: TextToSpeechViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
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