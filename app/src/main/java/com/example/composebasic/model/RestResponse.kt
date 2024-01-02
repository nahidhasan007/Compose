package com.example.composebasic.model

data class RestResponse<T> (
    val code: String,
    val message: String,
    val response: T
)
