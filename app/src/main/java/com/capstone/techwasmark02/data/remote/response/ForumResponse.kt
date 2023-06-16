package com.capstone.techwasmark02.data.remote.response

import com.google.gson.annotations.SerializedName

data class ForumResponse(
    val error: String,
    val forum: List<Forum>,
    val message: String
)

data class Forum(
    @SerializedName("Postedby")
    val postedBy: String,
    val category: String,
    val content: String,
    val id: Int,
    val imageURL: String,
    val likes: Any,
    val location: String,
    val title: String
)