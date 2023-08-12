package com.example.symbolspeak_aac.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.symbolspeak_aac.*
import com.example.symbolspeak_aac.CustomSentences.SentenceEvent
import com.example.symbolspeak_aac.CustomSentences.SentenceState
import com.example.symbolspeak_aac.History.History

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    state: SentenceState,
    onEvent: (SentenceEvent) -> Unit,
    history: History
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavRoute.Home.path
    ) {
        addInfoScreen(this)

        addHomeScreen(this, history)

        addSettingsScreen(this)

        addCustomSentencesScreen(this, state, onEvent)
    }
}

private fun addHomeScreen(
    navGraphBuilder: NavGraphBuilder,
    history: History
) {
    navGraphBuilder.composable(route = NavRoute.Home.path) {
        HomeScreen(history = history)
    }
}

private fun addInfoScreen(
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Info.path) {
        InfoScreen()
    }
}

private fun addSettingsScreen(
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Settings.path) {
        SettingsScreen()
    }
}

private fun addCustomSentencesScreen(
    navGraphBuilder: NavGraphBuilder,
    state: SentenceState,
    onEvent: (SentenceEvent) -> Unit
) {
    navGraphBuilder.composable(route = NavRoute.CustomSentences.path) {
        SentencesScreen(state = state, onEvent = onEvent)
    }
}