package com.capstone.techwasmark02.repository

import com.capstone.techwasmark02.data.remote.apiService.TechwasPredictionApi
import com.capstone.techwasmark02.data.remote.response.DetectionsResultResponse
import com.capstone.techwasmark02.ui.common.UiState
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class TechwasPredictionApiRepository @Inject constructor(
    private val predictionApi: TechwasPredictionApi
) {

    suspend fun predictWaste(file: File): UiState<DetectionsResultResponse> {
        val imageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData(
            name = "file",
            filename = file.name,
            body = imageFile
        )

        val response = try {
            predictionApi.predict(imageMultiPart)
        } catch (e: Exception) {
            return UiState.Error(message = "fail to predict, ${e.message}")
        }
        return UiState.Success(data = response, message = "Success to predict e-waste")
    }

}