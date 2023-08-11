package com.example.symbolspeak_aac.CustomSentences

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.symbolspeak_aac.CustomSentences.Sentence
import com.example.symbolspeak_aac.CustomSentences.SentenceDao

@Database(
    entities = [Sentence::class],
    version = 1
)
abstract class SentenceDatabase: RoomDatabase() {
    abstract val dao: SentenceDao
}