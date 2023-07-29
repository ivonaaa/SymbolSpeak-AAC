package com.example.symbolspeak_aac.InfoScreenFiles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.symbolspeak_aac.R

@Composable
fun PeopleWhoNeedAACScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.peopleWhoNeedAAC))
        Text(text = stringResource(id = R.string.definePeopleWhoNeedAAC))
    }
}