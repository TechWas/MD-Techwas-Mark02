package com.capstone.techwasmark02.data.remote.apiService


import com.capstone.techwasmark02.data.remote.response.DetectionsResultResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface TechwasPredictionApi {

    @Multipart
    @POST("predict/")
    suspend fun predict(
        @Part imageFile: MultipartBody.Part
    ) : DetectionsResultResponse

    companion object {
        const val BASE_URL = "http://34.126.151.206/"
//        const val BASE_URL = "https://e-waste-model-deployment-1gb-fwd5gpydiq-uc.a.run.app/"
    }
}