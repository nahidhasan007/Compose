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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var messages = mutableListOf<com.example.composebasic.model.Message>()
                    messages.add(com.example.composebasic.model.Message("Mary", "Hopper"))
                    messages.add(com.example.composebasic.model.Message("Mary2", "Hopper2"))
                    messages.add(com.example.composebasic.model.Message("Mary3", "Hopper3"))
                    messages.add(com.example.composebasic.model.Message("Mary4", "Hopper4"))
                    showMessages(messages = messages)
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
fun showMessages(messages: List<com.example.composebasic.model.Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(msg = message)
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

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded}) {
            Text(
                text = msg.title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.size(4.dp))
            Surface(shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)) {
                Text(
                    text = msg.subTitle,
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
            showMessages(messages = mutableListOf())
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