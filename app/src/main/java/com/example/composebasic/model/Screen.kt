package com.example.composebasic.model


const val countryName = "country_name"
const val countryCode = "country_code"

sealed class Screen(val route: String) {

    object Login : Screen(route = "login")
    object Country : Screen(route = "country")

    object CheckVM : Screen(route = "checkVm")
    object CountryDetails :
        Screen(route = "country_details" + "/{$countryName}" + "/{$countryCode}") {
        fun getRoute(name: String, code: String): String {
            return "country_details" + "/$name" + "/$code"
        }
    }
}