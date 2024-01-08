package com.example.composebasic.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composebasic.viewmodel.SharedViewModel

@Composable
fun CheckCurrentValues(sharedViewModel: SharedViewModel) {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Welcome to SharedViewModel view!",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
        Row(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Country : ${sharedViewModel.currentCountry.value.name}",
                modifier = Modifier.padding(end = 24.dp)
            )
            Text(text = "Code : ${sharedViewModel.currentCountry.value.code}")
        }
    }
}