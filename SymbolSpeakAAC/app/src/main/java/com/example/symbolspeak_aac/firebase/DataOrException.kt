package com.example.symbolspeak_aac.firebase

data class DataOrException<T, E : Exception?>(
    var data: T? = null,
    var e: E? = null
)