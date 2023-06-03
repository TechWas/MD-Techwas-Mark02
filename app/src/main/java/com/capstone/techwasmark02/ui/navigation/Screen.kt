package com.capstone.techwasmark02.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
    object Camera: Screen(route = "camera")
    object DetectionResult: Screen(route = "detectionResult")
}
