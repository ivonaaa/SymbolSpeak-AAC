package com.example.symbolspeak_aac

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import com.example.symbolspeak_aac.infoScreenFiles.PeopleWhoNeedAACScreen
import com.example.symbolspeak_aac.infoScreenFiles.TypesOfAACScreen
import com.example.symbolspeak_aac.infoScreenFiles.WhatIsAACScreen
import com.example.symbolspeak_aac.infoScreenFiles.WindowCenterOffsetPositionProvider

@Composable
fun InfoScreen(
) {
    var checked1 by remember { mutableStateOf(false) }
    var checked2 by remember { mutableStateOf(false) }
    var checked3 by remember { mutableStateOf(false) }

    Column {
        Column(
            modifier = Modifier
                .padding(5.dp)
        ) {
            Text(text = stringResource(id = R.string.infoIntro), fontSize = 20.sp)
        }

        Column {
            TextButton(
                onClick = { checked1 = true }
            ) {
                Text("* What is AAC?", fontSize = 20.sp)
            }

            if (checked1) {
                Popup(
                    popupPositionProvider =
                    WindowCenterOffsetPositionProvider(),
                    onDismissRequest = { checked1 = false },
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize(1f),
                        border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                        shape = RoundedCornerShape(0.dp),
                        color = Color.White.copy(alpha = 1.0f),
                    ) {
                        Column(
                            modifier = Modifier.padding(1.dp)
                        ) {
                            // Composable content to be shown in the Popup
                            Row {
                                TextButton(onClick = { checked1 = false }) {
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


            TextButton(onClick = { checked2 = true }) {
                Text("* People who need AAC", fontSize = 20.sp)
            }

            if (checked2) {
                Popup(
                    popupPositionProvider =
                    WindowCenterOffsetPositionProvider(),
                    onDismissRequest = { checked2 = false },
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize(1f),
                        border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                        shape = RoundedCornerShape(0.dp),
                        color = Color.White.copy(alpha = 1.0f),
                    ) {
                        Column(
                            modifier = Modifier.padding(1.dp)
                        ) {
                            // Composable content to be shown in the Popup
                            Row {
                                TextButton(onClick = { checked2 = false }) {
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

            TextButton(onClick = { checked3 = true }) {
                Text("* Types of AAC", fontSize = 20.sp)
            }

            if (checked3) {
                Popup(
                    popupPositionProvider =
                    WindowCenterOffsetPositionProvider(),
                    onDismissRequest = { checked3 = false },
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize(1f),
                        border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                        shape = RoundedCornerShape(0.dp),
                        color = Color.White.copy(alpha = 1.0f),
                    ) {
                        Column(
                            modifier = Modifier.padding(1.dp)
                        ) {
                            // Composable content to be shown in the Popup
                            Row {
                                TextButton(onClick = { checked3 = false }) {
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