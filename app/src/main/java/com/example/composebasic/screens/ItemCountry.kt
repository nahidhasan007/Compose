package com.example.composebasic.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composebasic.R
import com.example.composebasic.model.Nationality
import com.example.composebasic.model.Screen
import com.example.composebasic.viewmodel.MainViewModel
import com.example.composebasic.viewmodel.SharedViewModel

@Composable
fun Country(country: Nationality, navController: NavController, viewModel: MainViewModel?) {

    val sharedViewModel: SharedViewModel = viewModel()
    Row(modifier = Modifier.padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.dp),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        var isExpanded by remember {
            mutableStateOf(false)
        }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            label = "Checking surface",
        )

        Column(modifier = Modifier.clickable {
            viewModel?.currentCountryState?.value = country
//            sharedViewModel.currentCountry.value = country
//            navController.currentBackStackEntry?.arguments?.putParcelable("country",country)
            navController.navigate(route = Screen.CountryDetails.getRoute(country.name, country.code))
        }) {
            country.name?.let {name->
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = Color(R.color.white),
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
            }
        }
    }
}