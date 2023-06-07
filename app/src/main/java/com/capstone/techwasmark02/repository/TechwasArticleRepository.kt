package com.capstone.techwasmark02.repository

import com.capstone.techwasmark02.data.remote.apiService.TechwasArticleApi
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.data.remote.response.SingleArticleResponse
import com.capstone.techwasmark02.ui.common.UiState
import javax.inject.Inject

class TechwasArticleRepository @Inject constructor(
    private val articleApi: TechwasArticleApi
) {
    suspend fun getAllArticle(): UiState<ArticleResultResponse> {
        val response = try {
            articleApi.getAllArticle()
        } catch (e: Exception) {
            return UiState.Error(message = "fail to fetch article, ${e.message}")
        }
        val articleResultResponse = ArticleResultResponse(
            articleList = response.componentList,
            error = response.error,
            message = response.message
        )

        return UiState.Success(data = articleResultResponse, message = "Success to fetch article")
    }

    suspend fun getArticleById(id: Int): UiState<SingleArticleResponse> {
        val response = try {
            articleApi.getArticleById(id)
        } catch (e: Exception) {
            return UiState.Error(message = "fail to fetch article, ${e.message}")
        }
        return UiState.Success(data = response, message = "Success to fetch article")
    }

    suspend fun getArticleByName(name: String): UiState<ArticleResultResponse> {
        val response = try {
            articleApi.getArticleByName(name)
        } catch (e: Exception) {
            return UiState.Error(message = "fail to fetch article, ${e.message}")
        }
        return UiState.Success(data = response, message = "Success to fetch article")
    }

    suspend fun getArticleByComponentId(userToken: String = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySUQiOiJ1c2VyQGV4YW1wbGUuY29tIiwiZXhwaXJ5IjoxNjg1ODcyMDkwLjMwNjU4ODJ9.cvaEjnnWe4Z3Hl-ImAIKyguTWeuntb6vOuwGCa1rr2w", id: Int): UiState<ArticleResultResponse> {
        val response = try {
            articleApi.getArticleByComponentId(token = userToken, compid = id)
        } catch (e: Exception) {
            return UiState.Error(message = "fail to fetch article, ${e.message}")
        }
        return UiState.Success(data = response, message = "Success to fetch article")
    }
}