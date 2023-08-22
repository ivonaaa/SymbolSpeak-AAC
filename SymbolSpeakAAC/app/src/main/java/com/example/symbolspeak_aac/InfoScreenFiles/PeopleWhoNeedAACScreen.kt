package com.example.symbolspeak_aac.InfoScreenFiles

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.symbolspeak_aac.R
import com.example.symbolspeak_aac.SettingsScreenFiles.UserSettings

@Composable
fun PeopleWhoNeedAACScreen() {
    val context = LocalContext.current
    val store = UserSettings(context)
    val fontSize = store.getFontSize.collectAsState(initial = 16)

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.peopleWhoNeedAAC),
            fontSize = fontSize.value.sp)
        AsyncImage(
            model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtq2QOcAiDNmURgxbpcos-vY4s9rbYUjEPqQ&usqp=CAU",
            contentDescription = "Person using aac device",
            modifier = Modifier
                .fillMaxWidth()
                .size(250.dp)
                .padding(10.dp),
        )
        Text(text = stringResource(id = R.string.definePeopleWhoNeedAAC),
            fontSize = fontSize.value.sp,
            textAlign = TextAlign.Justify)
    }
}