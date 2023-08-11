package com.example.symbolspeak_aac

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.symbolspeak_aac.CustomSentences.SentenceEvent
import com.example.symbolspeak_aac.CustomSentences.SentenceState

@Composable
fun AddSentenceDialog(
    state: SentenceState,
    onEvent: (SentenceEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
                           onEvent(SentenceEvent.HideDialog)
        },
        title = { Text(text = "Add sentence")},
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(
                    value = state.text,
                    onValueChange = {
                        onEvent(SentenceEvent.SetText(it))
                                    },
                    placeholder = {
                        Text(text = "Enter sentence")
                    }
                )
            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Button(onClick = {
                    onEvent(SentenceEvent.SaveSentence)
                }) {
                    Text(text = "Save sentence")
                }
            }
        }
    )
}