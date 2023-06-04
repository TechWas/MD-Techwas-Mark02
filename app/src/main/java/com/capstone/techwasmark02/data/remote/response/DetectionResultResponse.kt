package com.capstone.techwasmark02.data.remote.response

import com.google.gson.annotations.SerializedName

//data class DetectResult(
//    val predictions: Predictions,
//    val time_taken: String
//)

//data class Predictions(
//    @SerializedName("Cable")
//    val cable: Double?,
//    @SerializedName("CRT_TV")
//    val crtTv: Double?,
//    @SerializedName("E-kettle")
//    val eKettle: Double?,
//    @SerializedName("Refrigerator")
//    val refrigerator: Double?,
//    @SerializedName("Keyboard")
//    val keyboard: Double?,
//    @SerializedName("Laptop")
//    val laptop: Double?,
//    @SerializedName("Light Bulb")
//    val lightBulb: Double?,
//    @SerializedName("Monitor")
//    val monitor: Double?,
//    @SerializedName("Mouse")
//    val mouse: Double?,
//    @SerializedName("PCB")
//    val pcb: Double?,
//    @SerializedName("Phone")
//    val phone: Double?,
//    @SerializedName("Printer")
//    val printer: Double?,
//    @SerializedName("Rice Cooker")
//    val riceCooker: Double?,
//    @SerializedName("Washing Machine")
//    val washingMachine: Double?
//
//)
//
//data class ResultComponentType(
//    val name: String,
//    val percentage: Double
//)
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


