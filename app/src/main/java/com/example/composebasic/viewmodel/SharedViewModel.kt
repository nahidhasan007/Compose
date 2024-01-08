package com.example.composebasic.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composebasic.model.Nationality

class SharedViewModel : ViewModel() {
    var currentCountry: MutableState<Nationality> = mutableStateOf(Nationality("BAN", "Bangladesh"))


    private val _countries: MutableState<List<Nationality>> = mutableStateOf(emptyList())
    val countries: State<List<Nationality>> = _countries

    fun storeCountry(nations : List<Nationality>) {
        _countries.value = nations
    }


}