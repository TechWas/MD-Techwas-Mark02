package com.capstone.techwasmark02.data.remote.response

data class ComponentResponse(
    val componentList: ComponentList,
    val error: String,
    val message: String
)

data class ComponentList(
    val desc: String,
    val example: String,
    val id: Int,
    val name: String
)