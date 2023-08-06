package com.example.symbolspeak_aac.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.symbolspeak_aac.UserSettings

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
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
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}