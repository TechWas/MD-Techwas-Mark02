package com.capstone.techwasmark02.repository

import com.capstone.techwasmark02.data.remote.apiService.TechwasForumApi
import com.capstone.techwasmark02.data.remote.response.ForumResponse
import com.capstone.techwasmark02.ui.common.UiState
import java.lang.Exception
import javax.inject.Inject

class TechwasForumApiRepository @Inject constructor(
    private val forumApi: TechwasForumApi
) {

    suspend fun fetchAllForum(): UiState<ForumResponse> {
        val response = try {
            forumApi.fetchAllForum()
        } catch (e: Exception) {
            return UiState.Error(message = "fail to fetch forum, ${e.message}")
        }
        return UiState.Success(data = response, message = "Success to fetch forum")
    }

    suspend fun fetchForumById(id: Int): UiState<ForumResponse> {
        val response = try {
            forumApi.fetchForumById(id)
        } catch (e: Exception) {
            return UiState.Error(message = "fail to fetch forum, ${e.message}")
        }
        return UiState.Success(data = response, message = response.message)
    }

    suspend fun fetchForumByCategory(category: String): UiState<ForumResponse> {
        val response = try {
            forumApi.fetchForumByCategory(category)
        } catch (e: Exception) {
            return UiState.Error(message = "fail to fetch forum, ${e.message}")
        }
        return UiState.Success(data = response, message = response.message)
    }
}