package com.example.symbolspeak_aac.InfoScreenFiles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.symbolspeak_aac.R
import com.example.symbolspeak_aac.SettingsScreenFiles.UserSettings

@Composable
fun TypesOfAACScreen() {
    val context = LocalContext.current
    val store = UserSettings(context)
    val fontSize = store.getFontSize.collectAsState(initial = 16)

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.typesOfAAC),
            fontSize = fontSize.value.sp)
        Text(text = stringResource(id = R.string.defineTypesOfAac),
            fontSize = fontSize.value.sp,
            textAlign = TextAlign.Justify)
    }
}