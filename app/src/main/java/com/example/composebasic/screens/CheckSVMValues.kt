package com.example.composebasic.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composebasic.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckCurrentValues(sharedViewModel: SharedViewModel) {
    var country: String by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
        OutlinedTextField(
            value = country,
            onValueChange = { country = it },
            label = { Text("Search Country") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 8.dp, start = 10.dp)
        )
        Button(
            onClick = {
                sharedViewModel.countries.value.forEach {
                    if (it.name == country) {
                        Toast.makeText(context, "${country} found", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .height(40.dp) // Adjust the height of the button as needed
                .padding(start = 8.dp, end = 10.dp)
        ) {
            Text("Search")
        }
        Log.e("CheckAllCountries", sharedViewModel.countries.value.toString())
    }
}