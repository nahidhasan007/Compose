package com.example.composebasic.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composebasic.model.Nationality

class SharedViewModel : ViewModel() {
    var currentCountry : MutableState<Nationality> = mutableStateOf(Nationality("BAN","Bangladesh"))

}