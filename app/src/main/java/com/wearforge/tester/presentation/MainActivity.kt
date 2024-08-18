package com.wearforge.tester.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.mutualmobile.composesensors.AccelerometerSensorState
import com.mutualmobile.composesensors.SensorDelay
import com.mutualmobile.composesensors.rememberAccelerometerSensorState
import com.wearforge.tester.presentation.sensors.Accelerometer
import com.wearforge.tester.presentation.theme.WearForgeTesterTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    WearForgeTesterTheme {
        val navController = rememberSwipeDismissableNavController()
        SwipeDismissableNavHost(
            navController = navController,
            startDestination = NavigationItem.Main.route
        ) {
            composable(NavigationItem.Main.route) {
                MainMenu(navController)
            }
            composable(NavigationItem.Accelerometer.route) {
                    AccelerometerMenu(navController)
            }
        }
    }
}

@Composable
fun MainMenu(navConntroller: NavHostController) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Chip(onClick = {navConntroller.navigate(NavigationItem.Accelerometer.route)}, label = {
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
        Button(onClick = { navConntroller.navigate(NavigationItem.Info.route) }) {

        }
    }
}

@Composable
fun AccelerometerMenu(navConntroller: NavHostController) {
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
        Chip(onClick = {navConntroller.navigate(NavigationItem.Main.route)}, label = {
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