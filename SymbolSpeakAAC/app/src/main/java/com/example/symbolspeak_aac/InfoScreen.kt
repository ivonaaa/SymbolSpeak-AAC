package com.example.symbolspeak_aac

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import com.example.symbolspeak_aac.InfoScreenFiles.PeopleWhoNeedAACScreen
import com.example.symbolspeak_aac.InfoScreenFiles.TypesOfAACScreen
import com.example.symbolspeak_aac.InfoScreenFiles.WhatIsAACScreen
import com.example.symbolspeak_aac.InfoScreenFiles.WindowCenterOffsetPositionProvider

@Composable
fun InfoScreen(
) {
    var isOnWhatIsAAC by remember { mutableStateOf(false) }
    var isOnPeopleWhoUseAAC by remember { mutableStateOf(false) }
    var isOnTypesOfAAC by remember { mutableStateOf(false) }

    Column {
        Column(
            modifier = Modifier
                .padding(5.dp)
        ) {
            Text(text = stringResource(id = R.string.infoIntro), fontSize = 20.sp)
        }

        Column {
            TextButton(
                onClick = { isOnWhatIsAAC = true }
            ) {
                Text("* What is AAC?", fontSize = 20.sp)
            }

            if (isOnWhatIsAAC) {
                Popup(
                    popupPositionProvider =
                    WindowCenterOffsetPositionProvider(),
                    onDismissRequest = { isOnWhatIsAAC = false },
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize(1f),
                        border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                        shape = RoundedCornerShape(0.dp),
                        color = MaterialTheme.colors.background,
                    ) {
                        Column(
                            modifier = Modifier.padding(1.dp)
                        ) {
                            // Composable content to be shown in the Popup
                            Row {
                                TextButton(onClick = { isOnWhatIsAAC = false }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                    Text(text = "Back", fontSize = 20.sp)
                                }
                            }
                            WhatIsAACScreen()
                        }
                    }
                }
            }


            TextButton(onClick = { isOnPeopleWhoUseAAC = true }) {
                Text("* People who need AAC", fontSize = 20.sp)
            }

            if (isOnPeopleWhoUseAAC) {
                Popup(
                    popupPositionProvider =
                    WindowCenterOffsetPositionProvider(),
                    onDismissRequest = { isOnPeopleWhoUseAAC = false },
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize(1f),
                        border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                        shape = RoundedCornerShape(0.dp),
                        color = MaterialTheme.colors.background,
                    ) {
                        Column(
                            modifier = Modifier.padding(1.dp)
                        ) {
                            // Composable content to be shown in the Popup
                            Row {
                                TextButton(onClick = { isOnPeopleWhoUseAAC = false }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                    Text(text = "Back", fontSize = 20.sp)
                                }
                            }
                            PeopleWhoNeedAACScreen()
                        }
                    }
                }
            }

            TextButton(onClick = { isOnTypesOfAAC = true }) {
                Text("* Types of AAC", fontSize = 20.sp)
            }

            if (isOnTypesOfAAC) {
                Popup(
                    popupPositionProvider =
                    WindowCenterOffsetPositionProvider(),
                    onDismissRequest = { isOnTypesOfAAC = false },
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize(1f),
                        border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                        shape = RoundedCornerShape(0.dp),
                        color = MaterialTheme.colors.background,
                    ) {
                        Column(
                            modifier = Modifier.padding(1.dp)
                        ) {
                            // Composable content to be shown in the Popup
                            Row {
                                TextButton(onClick = { isOnTypesOfAAC = false }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                    Text(text = "Back", fontSize = 20.sp)
                                }
                            }
                            TypesOfAACScreen()
                        }
                    }
                }
            }
        }
    }
}