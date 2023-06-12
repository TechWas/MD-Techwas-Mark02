package com.capstone.techwasmark02.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
    object Forum: Screen(route = "forum")
    object Profile: Screen(route = "profile")
    object Camera: Screen(route = "camera")
    object Catalog: Screen(route = "catalog")
    object SingleCatalog: Screen(route = "singleCatalog")
    object DetectionResult: Screen(route = "detectionResult")
    object Article: Screen(route = "article")
    object SingleArticle: Screen(route = "singleArticle")
    object Splash: Screen(route = "splash")
    object Maps: Screen(route = "maps")
    object Setting: Screen(route = "setting")
}
