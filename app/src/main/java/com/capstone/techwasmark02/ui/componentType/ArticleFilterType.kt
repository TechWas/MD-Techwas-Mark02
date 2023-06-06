package com.capstone.techwasmark02.ui.componentType

sealed class ArticleFilterType(val type: String, val id: Int) {

    object General: ArticleFilterType(type = "General", id = 0)

    object Laptop: ArticleFilterType(type = "Laptop", id = 1)

    object Battery: ArticleFilterType(type = "Battery", id = 2)

}