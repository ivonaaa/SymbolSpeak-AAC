package com.example.symbolspeak_aac.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.symbolspeak_aac.settingsScreenFiles.UserSettings

private val OrangeColorPalette = lightColors(
    primary = Color(213,119,55,255),
    primaryVariant = Color(197,78,24,255),
    secondary = Color(230,177,99,255),
    background = Color(231,229,227,255),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

private val GreenColorPalette = lightColors(
    primary = Color(0,74,17,255),
    primaryVariant = Color(86, 180, 137, 39),
    secondary = Color(2,105,116,255),
    background = Color(188, 214, 232, 116),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun SymbolSpeakAACTheme(
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val store = UserSettings(context)
    val colorTheme = store.getChosenColorSet.collectAsState(initial = false)

    val colors = if (colorTheme.value) {
        OrangeColorPalette
    } else {
        GreenColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}