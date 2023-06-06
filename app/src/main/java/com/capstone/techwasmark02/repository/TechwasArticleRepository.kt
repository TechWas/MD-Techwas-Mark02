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
        return UiState.Success(data = response, message = "Success to fetch article")
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
}