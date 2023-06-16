package com.capstone.techwasmark02.ui.componentType

sealed class ArticleFilterType(val type: String, val id: Int) {

    object General: ArticleFilterType(type = "General", id = 0)

    object Battery: ArticleFilterType(type = "Battery", id = 1)

    object Cable: ArticleFilterType(type = "Cable", id = 2)

    object CrtTv: ArticleFilterType(type = "CRT TV", id = 3)

    object EKettle: ArticleFilterType(type = "E-kettle", id = 4)

    object Refrigerator: ArticleFilterType(type = "Refrigerator", id = 5)

    object Keyboard: ArticleFilterType(type = "Keyboard", id = 6)

    object Laptop: ArticleFilterType(type = "Laptop", id = 7)

    object LightBulb: ArticleFilterType(type = "Light Bulb", id = 8)

    object Monitor: ArticleFilterType(type = "Monitor", id = 9)

    object Mouse: ArticleFilterType(type = "Mouse", id = 10)

    object PCB: ArticleFilterType(type = "PCB", id = 11)

    object Printer: ArticleFilterType(type = "Printer", id = 12)

    object RiceCooker: ArticleFilterType(type = "Rice Cooker", id = 13)

    object WashingMachine: ArticleFilterType(type = "Washing Machine", id = 14)

    object Phone: ArticleFilterType(type = "Phone", id = 15)

}