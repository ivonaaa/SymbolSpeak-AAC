package com.example.symbolspeak_aac

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
    ) {
        PickSettings()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun PickSettings() {
    val context = LocalContext.current
    val tokenValue2 = remember {
        mutableStateOf(TextFieldValue())
    }
    val store = UserSettings(context)
    val tokenText1 = store.getChosenColorSet.collectAsState(initial = false)
    val tokenText2 = store.getFontSize.collectAsState(initial = 14)
    val tokenText3 = store.getTtsRate.collectAsState(initial = 1.0)
    val tokenValue1 = remember {
        mutableStateOf(false)
    }
    val tokenValue3 = remember {
        mutableStateOf(0f)
    }

    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Text(text = tokenText1.value.toString())
        Text(text = tokenText2.value.toString())
        Text(text = tokenText3.value.toString())

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Pick color theme: ")
        Switch(checked = tokenValue1.value, onCheckedChange = {tokenValue1.value = it})

        Text(text = "Pick font size: ")
        TextField(
            value = tokenValue2.value,
            onValueChange = { tokenValue2.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = "Example of ${tokenText2.value} text font size.",
            fontSize = tokenText2.value.sp)

        Text(text = "Pick voice rate: ")
        Slider(
            value = tokenValue3.value,
            onValueChange = { sliderValue_ ->
                tokenValue3.value = sliderValue_
            },
            onValueChangeFinished = {
                // this is called when the user completed selecting the value

            },
            valueRange = 0f..1f
        )

        Text(text = tokenValue3.value.toString())

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    if (tokenValue2.value.text.toInt() < 16) {
                        tokenValue2.value = TextFieldValue("16")
                    }
                    if (tokenValue3.value < 0.0 || tokenValue3.value > 1.0) {
                        tokenValue3.value = 1.0F
                    }
                    store.saveToken(tokenValue1.value,
                        tokenValue2.value.text.toInt(),
                        tokenValue3.value)
                }
            }
        ) {
            Text(text = "Update")
        }
    }
}
