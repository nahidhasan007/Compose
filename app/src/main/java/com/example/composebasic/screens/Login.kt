package com.example.composebasic.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composebasic.R
import com.example.composebasic.model.Screen
import com.example.composebasic.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController?) {
    var username: String by remember { mutableStateOf("") }

    val context = LocalContext.current

    var password: String by remember {
        mutableStateOf("")
    }

    Scaffold(Modifier.background(Color.White)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.login),
                    contentDescription = "Login Image",
                    modifier = Modifier
                        .size(120.dp)
                )
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 16.dp, bottom = 42.dp)
                )

                // Input fields for username
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                // Input fields for password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary
                    ),
                    onClick = {
                        if (validateLogin(username, password)) {
                            navController?.navigate(route = Screen.Country.route)
                            Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Invalid Credentials!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text("Login")
                }

                TextButton(
                    onClick = { /* Handle forgot password click */ },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = "Forgot Password?",
                        color = Primary
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Don't have an account?",
                        color = Primary,
                        modifier = Modifier.weight(1f)
                    )

                    TextButton(
                        onClick = { /* Handle sign up click */ }
                    ) {
                        Text(
                            text = "Sign Up",
                            color = Primary
                        )
                    }
                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary
                    ),
                    onClick = {
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Skip")
                }
            }

        }
    }
}

fun validateLogin(username: String, password: String): Boolean {
    return username == "Nahid" && password == "nahid351"
}


@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(null)
}