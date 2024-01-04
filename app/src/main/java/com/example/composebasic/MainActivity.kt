package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.composebasic.navaigation.appNavigation
import com.example.composebasic.network.EndPoint
import com.example.composebasic.network.ServiceGenerator
import com.example.composebasic.repo.CountryListRepo
import com.example.composebasic.ui.theme.ComposeBasicTheme
import com.example.composebasic.ui.theme.Primary
import com.example.composebasic.viewmodel.MainVMF
import com.example.composebasic.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val apiService by lazy {
        ServiceGenerator.createService(EndPoint::class.java)
    }

    private val countryListRepo by lazy {
        CountryListRepo(apiService)
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            MainVMF(countryListRepo)
        )[MainViewModel::class.java]
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "B2B Demo",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(120.dp)
                                )
                            }, colors = TopAppBarDefaults.largeTopAppBarColors(
                                containerColor = Primary,
                                titleContentColor = Color.White,
                            ),
                            navigationIcon = {
//                                Icon(
//                                    Icons.Default.ArrowBack,
//                                    contentDescription = "Back"
//                                )
                            }
                        )
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        appNavigation(viewModel)
                    }

                }
            }
        }
    }
}
