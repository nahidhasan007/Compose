package com.example.composebasic.navaigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composebasic.model.Nationality
import com.example.composebasic.model.Screen
import com.example.composebasic.model.countryCode
import com.example.composebasic.model.countryName
import com.example.composebasic.screens.CheckCurrentValues
import com.example.composebasic.screens.CountryDetails
import com.example.composebasic.screens.CountryList
import com.example.composebasic.screens.LoginScreen
import com.example.composebasic.screens.OneWaySearch
import com.example.composebasic.viewmodel.MainViewModel
import com.example.composebasic.viewmodel.SharedViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun appNavigation(viewModel: MainViewModel?) {
    val sharedViewModel: SharedViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.Country.route) {
            CountryList(viewModel = viewModel, navController,sharedViewModel)

        }
        composable(
            route = Screen.CountryDetails.route, arguments = listOf(
                navArgument(name = countryName) {
                    type = NavType.StringType
//                    nullable = true
                },
                navArgument(name = countryCode) {
                    type = NavType.StringType
                },

                )
        ) { navBackStackEntry ->
            val countryName = navBackStackEntry.arguments?.getString(countryName)
            val countryCode = navBackStackEntry.arguments?.getString(countryCode)


            if (countryCode != null && countryName != null) {
                CountryDetails(countryName = countryName, countryCode = countryCode, navController)
                sharedViewModel.currentCountry.value = Nationality(countryCode, countryName)
            }
        }
        
        composable(route = Screen.CheckVM.route){
            CheckCurrentValues(sharedViewModel = sharedViewModel, navController)
        }

        composable(route = Screen.OneWay.route){
            OneWaySearch(viewModel)
        }
    }
}