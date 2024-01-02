package com.example.composebasic

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composebasic.model.Nationality
import com.example.composebasic.network.EndPoint
import com.example.composebasic.network.ServiceGenerator
import com.example.composebasic.repo.CountryListRepo
import com.example.composebasic.ui.theme.ComposeBasicTheme
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(R.color.white)
                ) {
                    showMessages(this, viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showMessages(current: LifecycleOwner?, viewModel: MainViewModel?) {

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
                        titleContentColor = Color.White),
                    navigationIcon = {
                        IconButton(onClick = { /* Handle navigation click */ }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
                        Country(country = country)
                    }
                }
            }
        }
    }

}


@Composable
fun MessageCard(msg: com.example.composebasic.model.Message) {
    Row(modifier = Modifier.padding(8.dp)) {
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

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.size(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.subTitle,
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
    }
}

@Composable
fun Country(country: Nationality) {
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

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = country.name,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
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


@Preview(name = "Dark Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun previewMessageCard() {
    ComposeBasicTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            showMessages(null, null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeBasicTheme {
        Greeting("Android")
    }
}

@Composable
fun appNavigation(current: LifecycleOwner?, viewModel: MainViewModel?){
    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "main") {
//        composable(route = "main"){
//            showMessages(current = current, viewModel = viewModel) {
//                navController.navigate("detail/")
//                }
//            }
//        }
//    }
}