package com.example.symbolspeak_aac.CustomSentences

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.symbolspeak_aac.CustomSentences.Sentence
import kotlinx.coroutines.flow.Flow

@Dao
interface SentenceDao {

    @Upsert
    suspend fun insertSentence(sentence: Sentence)

    @Delete
    suspend fun deleteSentence(sentence: Sentence)

    @Query("SELECT * FROM sentence")
    fun getSentences(): Flow<List<Sentence>>
}