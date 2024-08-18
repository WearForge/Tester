package com.wearforge.tester.presentation

enum class Screen {
    MAIN,
    ACCELEROMETER,
    INFO
}
sealed class NavigationItem(val route: String) {
    object Main : NavigationItem(Screen.MAIN.name)
    object Accelerometer : NavigationItem(Screen.ACCELEROMETER.name)
    object Info : NavigationItem(Screen.INFO.name)
}