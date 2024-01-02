package com.example.composebasic

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
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
                    color = MaterialTheme.colorScheme.background
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

@Composable
fun showMessages(current : LifecycleOwner?, viewModel: MainViewModel?) {

    val countries : State<List<Nationality>>? = viewModel?.countries?.collectAsState()
    LazyColumn {
        item {
            Text(text = "See Country",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // calling b2b getCountry endpoint on text clicked
                        viewModel?.getCountry()
                    })

        }
        if (countries != null) {
            items(countries.value){ it->
                Country(country = it)
            }
        }
//        items(messages) { message ->
//            MessageCard(msg = message)
//        }
    }
}

//@Composable
//fun ShowCountry(countries: List<Nationality>?) {
//    LazyColumn {
//        items(countries) { country ->
//          Country(country)
//        }
//    }
//}

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
                text = country.name,
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
                    text = country.code,
                    style = MaterialTheme.typography.displaySmall
                )
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
            showMessages( null,null)
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