package com.wearforge.tester.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
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
                Menus().MainMenu(navController)
            }
            composable(NavigationItem.Accelerometer.route) {
                Menus().AccelerometerMenu(navController)
            }
            composable(NavigationItem.Info.route) {
                Menus().InfoMenu(navController)
            }
            composable(NavigationItem.Light.route) {
                Menus().LightMenu(navController)
            }
        }
    }
}