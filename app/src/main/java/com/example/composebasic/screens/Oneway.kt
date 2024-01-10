package com.example.composebasic.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasic.R
import com.example.composebasic.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OneWaySearch() {

    var airportFrom: String by remember {
        mutableStateOf("DAC")
    }

    var airportTo: String by remember {
        mutableStateOf("Sin")
    }

    var dateOfFly: String by remember {
        mutableStateOf("30-12-1997")
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.White
    ) {
        Column {
            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .align(Alignment.CenterStart),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.b2b_home_bg),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        alignment = Alignment.Center
                    )

                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.flying_from),
                            color = Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )

                        TextField(value = airportFrom, onValueChange = { airportFrom = it })
                    }
                }


            }

            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .align(Alignment.CenterStart),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.b2b_home_bg),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        alignment = Alignment.Center
                    )

                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.flying_to),
                            color = Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )

                        TextField(value = airportTo, onValueChange = { airportTo = it })
                    }
                }


            }

            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .align(Alignment.CenterStart),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.b2b_home_bg),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        alignment = Alignment.Center
                    )

                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.fly_date),
                            color = Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )

                        TextField(value = dateOfFly, onValueChange = { dateOfFly = it })
                    }
                }


            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(Primary),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.search_flights))

            }
        }


    }
}


@Preview
@Composable
fun showOneWay() {
    OneWaySearch()
}