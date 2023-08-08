package com.example.symbolspeak_aac.firebase

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.symbolspeak_aac.symbol.Symbol
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class DataViewModel: ViewModel() {
    val state : MutableState<DataOrException<List<Symbol>, Exception>> = mutableStateOf(
        DataOrException(
            listOf(),
            Exception("")
        )
    )

    init {
        getData()
    }
    private fun getData() {
        viewModelScope.launch {
            state.value = getDataFromFirebase()
        }
    }
}

suspend fun getDataFromFirebase(): DataOrException<List<Symbol>, Exception> {

    val dataOrException = DataOrException<List<Symbol>, Exception>()
    try {
        dataOrException.data = FirebaseFirestore
            .getInstance()
            .collection("symbols")
            .orderBy("type", Query.Direction.ASCENDING)
            .get()
            .await()
            .map { document ->
                document.toObject(Symbol::class.java)
            }
    } catch (e: FirebaseFirestoreException) {
        dataOrException.e = e
    }
    return dataOrException
}
