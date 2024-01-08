package com.example.composebasic.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composebasic.model.Screen
import com.example.composebasic.ui.theme.Primary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetails(countryName: String, countryCode: String, navController: NavController) {

    Box(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp, 8.dp, 8.dp, 8.dp)
        ) {
            countryName?.let { Text(text = it) }
            countryCode?.let { Text(text = it) }
            Text(text = "CheckVMData",
                color = Primary,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.CheckVM.route)
                    }
                    .padding(24.dp)
            )

        }

    }

}