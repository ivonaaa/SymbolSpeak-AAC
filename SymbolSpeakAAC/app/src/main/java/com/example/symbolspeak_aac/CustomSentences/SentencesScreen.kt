package com.example.symbolspeak_aac

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.symbolspeak_aac.CustomSentences.SentenceEvent
import com.example.symbolspeak_aac.CustomSentences.SentenceState
import com.example.symbolspeak_aac.SettingsScreenFiles.UserSettings
import com.example.symbolspeak_aac.TextToSpeach.TextToSpeechViewModel

@Composable
fun SentencesScreen(
    state: SentenceState,
    onEvent: (SentenceEvent) -> Unit,
    ttsViewModel: TextToSpeechViewModel = viewModel()
) {
    val context = LocalContext.current
    val store = UserSettings(context)
    val ttsRate = store.getTtsRate.collectAsState(initial = 1.0)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(SentenceEvent.ShowDialog)
            }) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "Add sentence")
            }
        },
        modifier = Modifier.padding(16.dp)
    ) { padding ->
        if (state.isAddingSentence) {
            AddSentenceDialog(state = state, onEvent = onEvent)
        }
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.sentences) { sentence ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 2.dp, MaterialTheme.colors.secondary),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = sentence.text, modifier = Modifier.padding(5.dp))
                    Row(
                        modifier = Modifier
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(
                            onClick = {
                                onEvent(SentenceEvent.DeleteSentence(sentence))
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete sentence",
                            )
                        }
                        IconButton(
                            onClick = {
                                ttsViewModel.textToSpeech(
                                    context,
                                    ttsRate = ttsRate.value.toFloat(),
                                    text = sentence.text
                                )
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Play sentence"
                            )
                        }
                    }
                }
            }
        }
    }
}