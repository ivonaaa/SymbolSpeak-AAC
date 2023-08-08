package com.example.symbolspeak_aac.navigation

sealed class NavRoute(val path: String) {

    object Settings: NavRoute("Settings")

    object Home: NavRoute("home")

    object Info: NavRoute("AboutAAC")
}

