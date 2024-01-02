package com.example.composebasic.repo

import com.example.composebasic.network.EndPoint

class CountryListRepo(private val apiService : EndPoint) {
    suspend fun getCountry() = apiService.getCountryList()
}