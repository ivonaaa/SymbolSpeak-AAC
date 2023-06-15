package com.example.symbolspeak_aac

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoScreen(
) {
    val checked1 = remember { mutableStateOf(false) }
    val checked2 = remember { mutableStateOf(false) }
    val checked3 = remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Info Screen", fontSize = 40.sp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.whatIsAAC))
            Switch(
                checked = checked1.value,
                onCheckedChange = { checked1.value = it },
            )
        }
        if (checked1.value) {
            WhatIsAACScreen()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.typesOfAAC))
            Switch(
                checked = checked2.value,
                onCheckedChange = { checked2.value = it },
            )
        }
        if (checked2.value) {
            TypesOfAACScreen()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.peopleWhoNeedAAC))
            Switch(
                checked = checked3.value,
                onCheckedChange = { checked3.value = it },
            )
        }
        if (checked3.value) {
            PeopleWhoNeedAACScreen()
        }
    }
}

@Composable
fun WhatIsAACScreen() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .background(Color(0xFAFAFA), shape = RoundedCornerShape(15.dp))
            .border(2.dp, SolidColor(Color.Gray), shape = RoundedCornerShape(15.dp))
            .padding(16.dp)
    ) {
        Text(text = stringResource(id = R.string.whatIsAAC))
        Text(text = stringResource(id = R.string.defineAAC))

    }
}

@Composable
fun TypesOfAACScreen() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .background(Color(0xFAFAFA), shape = RoundedCornerShape(15.dp))
            .border(2.dp, SolidColor(Color.Gray), shape = RoundedCornerShape(15.dp))
            .padding(16.dp)
    ) {
        Text(text = stringResource(id = R.string.typesOfAAC))
        Text(text = stringResource(id = R.string.defineTypesOfAac))
    }
}

@Composable
fun PeopleWhoNeedAACScreen() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .background(Color(0xFAFAFA), shape = RoundedCornerShape(15.dp))
            .border(2.dp, SolidColor(Color.Gray), shape = RoundedCornerShape(15.dp))
            .padding(16.dp)
    ) {
        Text(text = stringResource(id = R.string.peopleWhoNeedAAC))
        Text(text = stringResource(id = R.string.definePeopleWhoNeedAAC))
    }
}