package com.example.symbolspeak_aac.SettingsScreenFiles

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserSettings(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userSettings")
        private val color = booleanPreferencesKey("color")
        private val chosenFontSize = intPreferencesKey("font_size")
        private val ttsRate = floatPreferencesKey("tts_rate")
    }

    val getChosenColorSet: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[color] ?: false
    }

    val getFontSize: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[chosenFontSize] ?: 14
    }

    val getTtsRate: Flow<Float> = context.dataStore.data.map { preferences ->
        preferences[ttsRate] ?: 1.0f
    }

    suspend fun saveToken(colorTheme: Boolean, fontSize: Int, ttsrate: Float) {
        context.dataStore.edit { preferences ->
            preferences[color] = colorTheme
            preferences[chosenFontSize] = fontSize
            preferences[ttsRate] = ttsrate
        }
    }
}