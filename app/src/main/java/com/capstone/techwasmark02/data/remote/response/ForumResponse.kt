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

data class ForumCommentResponse(
    val article: List<Article>,
    val error: String,
    val message: String
)

data class Article(
    val comment: String,
    val forumID: Int,
    val id: Int,
    val replyFrom: Int,
    val userID: String,
    val username: String
)

data class PostForumCommentResponse(
    val commentInfo: CommentInfo,
    val error: String,
    val message: String
)

data class CommentInfo(
    val Poster: String,
    val PosterID: Int,
    val comment: String,
    val forumID: Int
)

data class CreateForumResponse(
    val error: String,
    val message: String
)

data class ImageUrlResponse(
    val error: String,
    val imgURL: String,
    val message: String
)