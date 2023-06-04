package com.capstone.techwasmark02.data.remote.response

data class ComponentsResponse(
    val components: List<Component>,
    val error: String,
    val message: String
)

data class Component(
    val desc: String,
    val id: Int,
    val imageExample: String,
    val name: String
)