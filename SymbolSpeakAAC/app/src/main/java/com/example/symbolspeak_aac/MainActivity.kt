package com.example.symbolspeak_aac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.symbolspeak_aac.Navigation.MenuItem
import com.example.symbolspeak_aac.Navigation.NavRoute
import com.example.symbolspeak_aac.ui.theme.SymbolSpeakAACTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SymbolSpeakAACTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()
                Scaffold (
                    scaffoldState = scaffoldState,
                    topBar = {
                             AppBar (
                                 navController = navController,
                                 onNavigationIconClick = {
                                     scope.launch {
                                        scaffoldState.drawerState.open()
                                    }
                                 }
                             )
                    },
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(id = "home",
                                title = "Home",
                                contentDescription = "Go to home screen",
                                icon = Icons.Default.Home),
                                MenuItem(id = "AboutAAC",
                                    title = "What is AAC?",
                                    contentDescription = "Learn about AAC screen",
                                    icon = Icons.Default.Info),
                                MenuItem(id = "Settings",
                                    title = "Settings",
                                    contentDescription = "Go to settings screen",
                                    icon = Icons.Default.Settings),
                            ),
                            onItemClick = {
                                when(it.id) {
                                    "home" ->
                                    {
                                        navController.navigate(NavRoute.Home.path)
                                        scope.launch {
                                            scaffoldState.drawerState.close()
                                        }
                                    }
                                    "Settings" ->
                                    {
                                        navController.navigate(NavRoute.Settings.path)
                                        scope.launch {
                                            scaffoldState.drawerState.close()
                                        }
                                    }
                                    "AboutAAC" -> {
                                        navController.navigate(NavRoute.Info.path)
                                        scope.launch {
                                            scaffoldState.drawerState.close()
                                        }
                                    }
                                }
                            }
                        )
                    }
                ){ paddingValues ->
                    NavGraph(
                        modifier = Modifier.padding(
                            bottom = paddingValues.calculateBottomPadding()),
                        navController = navController
                    )
                }
            }
        }
    }
}
