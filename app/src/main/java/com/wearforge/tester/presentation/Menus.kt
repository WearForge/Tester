package com.wearforge.tester.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.mutualmobile.composesensors.AccelerometerSensorState
import com.mutualmobile.composesensors.SensorDelay
import com.mutualmobile.composesensors.rememberAccelerometerSensorState
import com.wearforge.tester.presentation.sensors.Accelerometer

class Menus {
    @Composable
    fun MainMenu(navController: NavHostController) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Chip(onClick = {navController.navigate(NavigationItem.Accelerometer.route)}, label = {
                Text(
                    text = "Accelerometer",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
                icon = {
                    Icon(
                        Icons.Default.Place,
                        contentDescription = "Sensor Icon",
                    )
                })
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Button(onClick = { navController.navigate(NavigationItem.Info.route) }) {
                Icon(Icons.Default.Info, contentDescription = "Info Button")
            }
        }
    }

    @Composable
    fun AccelerometerMenu(navController: NavHostController) {
        val AccelerometerState: AccelerometerSensorState = rememberAccelerometerSensorState(sensorDelay = SensorDelay.Fastest)

        val coor: Triple<Float, Float, Float> = Accelerometer().GetData(AccelerometerState)

        var Availablecolor: Color
        var IsAvailable: Boolean = AccelerometerState.isAvailable
        var AvaliableString: String

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (IsAvailable) {
                Availablecolor = Color.Green
                AvaliableString = "Yes"
            }
            else {
                Availablecolor = Color.Red
                AvaliableString = "No"
            }

            Text("Is avaliable? ${AvaliableString}", color = Availablecolor)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "X: ${coor.first}\n" +
                    "Y: ${coor.second}\n" +
                    "Z: ${coor.third}")

            Spacer(modifier = Modifier.height(8.dp))
            Chip(onClick = {navController.navigate(NavigationItem.Main.route)}, label = {
                Text(
                    text = "Main Menu",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
                icon = {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back to the main menu",
                    )
                })
        }
    }

    @Composable
    fun InfoMenu(navController: NavHostController) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Made by WearForge")

            Spacer(modifier = Modifier.height(8.dp))

            Chip(onClick = {navController.navigate(NavigationItem.Main.route)}, label = {
                Text(
                    text = "Main Menu",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
                icon = {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back to the main menu",
                    )
                })
        }
    }
}