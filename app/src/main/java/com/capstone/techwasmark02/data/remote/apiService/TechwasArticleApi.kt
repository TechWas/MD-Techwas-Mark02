package com.capstone.techwasmark02.data.remote.apiService

import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import retrofit2.Response
import retrofit2.http.GET

interface TechwasArticleApi {

    @GET("/allArticle")
    suspend fun getAllArticle(): ArticleResultResponse

    companion object {
        const val BASE_URL = "https://the-prophecy-fwd5gpydiq-uc.a.run.app/"
    }
}