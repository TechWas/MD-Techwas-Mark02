package com.capstone.techwasmark02.ui.componentType

sealed class ArticleFilterType(val type: String) {

    object General: ArticleFilterType(type = "General")

    object Laptop: ArticleFilterType(type = "Laptop")

    object Battery: ArticleFilterType(type = "Battery")

}