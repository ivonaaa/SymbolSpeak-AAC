package com.example.symbolspeak_aac.CustomSentences

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sentence(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String
)
