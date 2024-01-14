package com.example.composebasic.model

data class FlightSearchBody(
    val tripType : String,
    val adult : Int,
    val child : Int,
    val infant : Int,
    val classType : String,
    val flyDate : List<String>,
    val origin : List<String> = listOf(),
    val destination : List<String> = listOf(),
    val childAge : ArrayList<String> = arrayListOf()
)
