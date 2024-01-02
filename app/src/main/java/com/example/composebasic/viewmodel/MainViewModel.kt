package com.example.composebasic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.composebasic.model.Nationality
import com.example.composebasic.network.BaseResponse
import com.example.composebasic.repo.CountryListRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.concurrent.Flow

class MainViewModel(private val repo: CountryListRepo) : ViewModel() {

    val countries : StateFlow<List<Nationality>>
        get() = _countries
    private val _countries = MutableStateFlow<List<Nationality>>(emptyList())


    init {

    }

    fun getCountry() {
        viewModelScope.launch {
            val result = repo.getCountry()
            val country = result.body()?.response
            if (country != null) {
                operationOnResponse(country)
            }
        }
    }

    private fun operationOnResponse(data: List<Nationality>) {
        _countries.value = data
    }


}

class MainVMF(
    private val repo: CountryListRepo
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(repo) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}