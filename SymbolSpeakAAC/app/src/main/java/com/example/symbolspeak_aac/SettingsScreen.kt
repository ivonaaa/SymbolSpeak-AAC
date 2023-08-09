package com.example.symbolspeak_aac

import android.widget.NumberPicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import com.example.symbolspeak_aac.SettingsScreenFiles.SegmentedControl
import com.example.symbolspeak_aac.SettingsScreenFiles.UserSettings
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

@Composable
private fun PickSettings() {
    val context = LocalContext.current
    val store = UserSettings(context)
    val tokenText1 = store.getChosenColorSet.collectAsState(initial = false)
    val tokenText2 = store.getFontSize.collectAsState(initial = 14)
    val tokenText3 = store.getTtsRate.collectAsState(initial = 1.0)

    val tokenValue1 = remember {
        mutableStateOf(false)
    }
    val tokenValue2 = remember {
        mutableStateOf(16)
    }
    val tokenValue3 = remember {
        mutableStateOf(0f)
    }

    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Current settings: ")
        Text(text = "Color theme: " + if (tokenText1.value) "Orange theme" else "Green Theme")
        Text(text = "Font size: ${tokenText2.value}")
        Text(text = "Tts rate: ${tokenText3.value}")

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Pick color theme: ")
        val themes = listOf("Green theme", "Orange theme")
        SegmentedControl(
            items = themes,
            defaultSelectedItemIndex = 0
        ) {
            tokenValue1.value = it != 0
        }

        Text(text = "Pick font size: ")
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                NumberPicker(context).apply {
                    setOnValueChangedListener { numberPicker, _, _ ->
                        tokenValue2.value = numberPicker.value
                    }
                    minValue = 16
                    maxValue = 30
                }
            }
        )
        Text(text = "Example of ${tokenText2.value} text font size.",
            fontSize = tokenText2.value.sp)

        Text(text = "Pick voice rate: ")

        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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
        }
        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    store.saveSettings(tokenValue1.value,
                        tokenValue2.value,
                        tokenValue3.value)
                }
            }
        ) {
            Text(text = "Update")
        }
    }
}