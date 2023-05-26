package com.capstone.techwasmark02.ui.screen.maps

import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen() {
    val bucharest = LatLng(44.43, 26.09)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(bucharest, 10f)
    }

    GoogleMap(
        cameraPositionState = cameraPositionState
    )
}