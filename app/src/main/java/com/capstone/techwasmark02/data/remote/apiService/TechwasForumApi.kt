package com.capstone.techwasmark02.data.remote.apiService

import com.capstone.techwasmark02.data.remote.response.ForumResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TechwasForumApi {

    @GET("/forum/getall")
    suspend fun fetchAllForum(): ForumResponse

    @GET("/forum/id/{id}")
    suspend fun fetchForumById(
        @Path("id") id: Int
    ): ForumResponse

    @GET("/forum/category/{category}")
    suspend fun fetchForumByCategory(
        @Path("category") category: String
    ): ForumResponse

    companion object {
        const val BASE_URL = "https://backend-api-56g32wdmqa-uc.a.run.app/"
    }
}