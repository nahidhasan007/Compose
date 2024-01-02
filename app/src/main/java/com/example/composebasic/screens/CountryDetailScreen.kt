package com.example.composebasic.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composebasic.model.Nationality

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetails(country: Nationality) {
    Box(
        modifier = Modifier
            .padding(10.dp)
    )
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Country Detail",
                textAlign = TextAlign.Center
            )
        })
    }) {
        Column(
            modifier = Modifier
                .padding(8.dp, 8.dp, 8.dp, 8.dp)
        ) {
            Text(text = country.name)
            Text(text = country.code)

        }

    }

}