package com.example.symbolspeak_aac.SettingsScreenFiles

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserSettings(private val context: Context) {
    companion object {
        private val Context.dataStore:
                DataStore<Preferences> by preferencesDataStore("userSettings")
        private val chosenColorTheme = booleanPreferencesKey("color")
        private val chosenFontSize = intPreferencesKey("font_size")
        private val chosenTtsRate = floatPreferencesKey("tts_rate")
    }

    val getChosenColorSet: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[chosenColorTheme] ?: false
    }

    val getFontSize: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[chosenFontSize] ?: 16
    }

    val getTtsRate: Flow<Float> = context.dataStore.data.map { preferences ->
        preferences[chosenTtsRate] ?: 1.0f
    }

    suspend fun saveSettings(colorTheme: Boolean, fontSize: Int, ttsRate: Float) {
        context.dataStore.edit { preferences ->
            preferences[chosenColorTheme] = colorTheme
            preferences[chosenFontSize] = fontSize
            preferences[chosenTtsRate] = ttsRate
        }
    }
}