package com.capstone.techwasmark02.data.remote.apiService

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface TechwasPredictionApi {

    @Multipart
    @POST("predict/")
    suspend fun predict(
        @Part imageFile: MultipartBody.Part
    ) : String

    companion object {
        const val BASE_URL = "https://e-waste-model-deployment-1gb-fwd5gpydiq-uc.a.run.app/"
    }
}