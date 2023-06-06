package com.capstone.techwasmark02.data.remote.apiService

import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.data.remote.response.SingleArticleResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface TechwasArticleApi {

    @GET("/allArticle")
    suspend fun getAllArticle(): ArticleResultResponse

    @GET("/article/{id}")
    suspend fun getArticleByComponentId(
        @Header("Authorization") token: String,
        @Query("compid") compid: Int
    ): ArticleResultResponse

    @GET("/article/id/{id}")
    suspend fun getArticleById(
        @Path("id") id: Int
    ): SingleArticleResponse

    @GET("/article/name/{name}")
    suspend fun getArticleByName(
        @Path("name") name: String
    ): ArticleResultResponse

    companion object {
        const val BASE_URL = "https://the-prophecy-fwd5gpydiq-uc.a.run.app/"
    }
}