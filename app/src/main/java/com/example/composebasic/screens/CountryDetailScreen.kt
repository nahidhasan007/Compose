package com.example.composebasic.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetails(countryName: String, countryCode: String) {

//    val sharedViewModel: SharedViewModel = viewModel()
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

        }

    }

}