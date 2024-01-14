package com.example.composebasic.network

import com.example.composebasic.model.Nationality
import com.example.composebasic.model.RestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoint {

    @GET("country")
    suspend fun getCountryList(): Response<RestResponse<List<Nationality>>>

    @GET("flight/search")
    // https://stg-b2b.api.sharetrip.net/api/v1/flight/search?tripType=OneWay&adult=2&child=0&infant=0&class=Economy&origin=DAC&destination=CXB&depart=2024-01-25
    suspend fun searchFlight(
        @Query("tripType") tripType: String,
        @Query("adult") adult: Int,
        @Query("child") child: Int,
        @Query("infant") infant: Int,
        @Query("class") classType: String,
        @Query("origin") origin: List<String>,
        @Query("destination") destination: List<String>,
        @Query("depart") depart: List<String>,
        @Query("childAge") childAge: ArrayList<String>? = null
    ): Response<Any>

}