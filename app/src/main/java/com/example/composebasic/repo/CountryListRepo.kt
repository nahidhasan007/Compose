package com.example.composebasic.repo

import com.example.composebasic.model.FlightSearchBody
import com.example.composebasic.network.EndPoint

class CountryListRepo(private val apiService: EndPoint) {
    suspend fun getCountry() = apiService.getCountryList()

    suspend fun getFlight(searchBody: FlightSearchBody) = apiService.searchFlight(
        searchBody.tripType,
        searchBody.adult,
        searchBody.child,
        searchBody.infant,
        searchBody.classType,
        searchBody.origin,
        searchBody.destination,
        searchBody.flyDate,
        searchBody.childAge
    )
}