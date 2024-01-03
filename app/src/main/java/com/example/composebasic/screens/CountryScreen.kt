package com.example.composebasic.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composebasic.model.Nationality
import com.example.composebasic.viewmodel.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryList(viewModel: MainViewModel?, navController: NavController) {

    val countries: State<List<Nationality>>? = viewModel?.countries?.collectAsState()

    Box(
        modifier = Modifier
            .background(color = Color.Blue) // Set your desired background color here
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Country list",
                            textAlign = TextAlign.Justify,
                            modifier = Modifier
                                .padding(start = 60.dp)
                                .fillMaxWidth()
                                .clickable {
                                    viewModel?.getCountry()
                                })
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = { /* Handle navigation click */ }) {
//                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                item {
                    Text(
                        color = Color.Black,
                        text = "See Country",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel?.getCountry()
                            }
                    )
                }
                if (countries != null) {
                    items(countries.value) { country ->
                        Country(country = country, navController, viewModel)
                    }
                }
            }
        }
    }

}
