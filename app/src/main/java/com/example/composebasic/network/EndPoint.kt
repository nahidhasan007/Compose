package com.example.composebasic.network

import com.example.composebasic.model.Nationality
import com.example.composebasic.model.RestResponse
import retrofit2.Response
import retrofit2.http.GET

interface EndPoint {

    @GET("country")
    suspend fun getCountryList(): Response<RestResponse<List<Nationality>>>

}