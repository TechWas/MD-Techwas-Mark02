package com.capstone.techwasmark02.data.remote.apiService

import com.capstone.techwasmark02.data.model.ForumCommentInfo
import com.capstone.techwasmark02.data.model.ForumToCreateInfo
import com.capstone.techwasmark02.data.remote.response.CreateForumResponse
import com.capstone.techwasmark02.data.remote.response.ForumCommentResponse
import com.capstone.techwasmark02.data.remote.response.ForumResponse
import com.capstone.techwasmark02.data.remote.response.ImageUrlResponse
import com.capstone.techwasmark02.data.remote.response.PostForumCommentResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface TechwasForumApi {

    @GET("/forum/getall")
    suspend fun fetchAllForum(): ForumResponse

    @GET("/forum/id/{id}")
    suspend fun fetchForumById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ForumResponse

    @GET("/forum/category/{category}")
    suspend fun fetchForumByCategory(
        @Path("category") category: String
    ): ForumResponse

    @POST("/forum/post")
    suspend fun createNewForum(
        @Header("Authorization") token: String,
        @Body forumToCreateInfo: ForumToCreateInfo
    ): CreateForumResponse

    @GET("/comments/byforumid/{forumid}")
    suspend fun fetchForumComment(
        @Path("forumid") forumid: Int
    ): ForumCommentResponse

    @POST("/comments/post")
    suspend fun postForumComment(
        @Header("Authorization") token: String,
        @Body forumCommentInfo: ForumCommentInfo
    ): PostForumCommentResponse


    @Multipart
    @POST("/forum/upimagepost")
    suspend fun uploadAndGetImage(
        @Header("Authorization") token: String,
        @Part imageFile: MultipartBody.Part
    ): ImageUrlResponse

    companion object {
        const val BASE_URL = "https://backend-api-t7biziab7a-et.a.run.app/"
    }
}