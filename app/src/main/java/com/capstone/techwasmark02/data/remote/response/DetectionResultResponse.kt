package com.capstone.techwasmark02.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetectionsResultResponse(
    val Image_Url: String,
    val predictions: List<Prediction>,
    val time_taken: String
)

data class Prediction(
    @SerializedName("Components ID")
    val componentId: Int,
    @SerializedName("Components Name")
    val componentName: String,
    @SerializedName("Components Value")
    val componentValue: Double
)



