package com.capstone.techwasmark02.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
    object Camera: Screen(route = "camera")
    object DetectionResult: Screen(route = "detectionResult")

    object Article: Screen(route = "article")

    object SingleArticle: Screen(route = "singleArticle")

    object Splash: Screen(route = "splash")

    object Maps: Screen(route = "maps")
}
