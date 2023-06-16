package com.capstone.techwasmark02.repository

import com.capstone.techwasmark02.data.model.ForumCommentInfo
import com.capstone.techwasmark02.data.model.ForumToCreateInfo
import com.capstone.techwasmark02.data.remote.apiService.TechwasForumApi
import com.capstone.techwasmark02.data.remote.response.CreateForumResponse
import com.capstone.techwasmark02.data.remote.response.ForumCommentResponse
import com.capstone.techwasmark02.data.remote.response.ForumResponse
import com.capstone.techwasmark02.data.remote.response.ImageUrlResponse
import com.capstone.techwasmark02.data.remote.response.PostForumCommentResponse
import com.capstone.techwasmark02.ui.common.UiState
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
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

    suspend fun fetchForumById(id: Int, userToken: String): UiState<ForumResponse> {
        val token = "Bearer $userToken"

        val response = try {
            forumApi.fetchForumById(
                id = id,
                token = token
            )
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

    suspend fun fetchForumCommentByForumId(forumId: Int): UiState<ForumCommentResponse> {
        val response = try {
            forumApi.fetchForumComment(forumid = forumId)
        } catch (e: Exception) {
            return UiState.Error(message = "fail to fetch comment, ${e.message}")
        }
        return UiState.Success(data = response, message = response.message)
    }

    suspend fun postForumComment(forumCommentInfo: ForumCommentInfo, userToken: String): UiState<PostForumCommentResponse> {
        val token = "Bearer $userToken"

        val response = try {
            forumApi.postForumComment(
                token,
                forumCommentInfo,
            )
        } catch (e: Exception) {
            return UiState.Error(message = "fail to post comment, ${e.message}")
        }
        return UiState.Success(data = response, message = response.message)
    }

    suspend fun createNewForum(forumToCreateInfo: ForumToCreateInfo, userToken: String): UiState<CreateForumResponse> {
        val token = "Bearer $userToken"

        val response = try {
            forumApi.createNewForum(forumToCreateInfo = forumToCreateInfo, token = token)
        } catch (e: Exception) {
            return UiState.Error(message = e.message ?: "Fail to create new forum")
        }
        return UiState.Success(data = response, message = response.message)
    }

    suspend fun uploadAndGetImageUrl(file: File, userToken: String): UiState<ImageUrlResponse> {
        val token = "Bearer ${userToken}"

        val imageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultiPart: MultipartBody.Part = MultipartBody.Part
            .createFormData(
                name = "file",
                filename = file.name,
                body = imageFile
            )

        val response = try {
            forumApi.uploadAndGetImage(
                token,
                imageMultiPart
            )
        } catch (e: Exception) {
            return UiState.Error(message = "fail to get image url, ${e.message}")
        }
        return UiState.Success(data = response, message = response.message)
    }

}