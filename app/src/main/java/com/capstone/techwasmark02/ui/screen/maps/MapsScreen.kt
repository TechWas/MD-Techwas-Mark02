package com.capstone.techwasmark02.ui.screen.maps

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.capstone.techwasmark02.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.tasks.await

data class MapMarkerInfo(
    val position: LatLng,
    val title: String,
    val snippet: String
)
@Composable
fun MapsScreen(navController: NavHostController) {
    val diy = LatLng(-7.782275587997325, 110.36709993087182)

    // posisi camera
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(diy, 12f)
    }

    // dummy marker
    val markerList = listOf(
        MapMarkerInfo(
            LatLng(-7.782589124314163, 110.38006889168956),
            "Marker 1",
            "Ini adalah marker 1"
        ),
        MapMarkerInfo(
            LatLng(-7.8041473764088085, 110.39441386554735),
            "Marker 2",
            "Ini adalah marker 2"
        ),
        MapMarkerInfo(
            LatLng(-7.790733483350418, 110.35766494375434),
            "Marker 3",
            "Ini adalah marker 3"
        ),
        MapMarkerInfo(
            LatLng(-7.767737238844195, 110.35476371308648),
            "Marker 4",
            "Ini adalah marker 4"
        ),
        MapMarkerInfo(
            LatLng(-7.774923700677788, 110.41182124955456),
            "Marker 5",
            "Ini adalah marker 5"
        ),
        MapMarkerInfo(
            LatLng(-7.7798743027989685, 110.33864576493167),
            "Marker 6",
            "Ini adalah marker 6"
        ),
        MapMarkerInfo(
            LatLng(-7.783068208639076, 110.3805524301342),
            "Marker 7",
            "Ini adalah marker 7"
        )
    )

    val context = LocalContext.current
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    var userLatLng by remember { mutableStateOf<LatLng?>(null) }
    var permissionGranted by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionGranted = isGranted
    }

    LaunchedEffect(permissionGranted) {
        if (permissionGranted) {
            val location = fusedLocationClient.lastLocation.await()
            if (location != null) {
                userLatLng = LatLng(location.latitude, location.longitude)
            }
        }
    }

    LaunchedEffect(Unit) {
        when {
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                // Permission already granted
                permissionGranted = true
            }
            else -> {
                // Request permission
                launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    GoogleMap(
        // posisi camera
        cameraPositionState = cameraPositionState,
        ) {
        markerList.forEach { marker ->
            Marker(
                state = MarkerState(
                    position = marker.position
                ),
                title = marker.title,
                snippet = marker.snippet
            )
        }

        userLatLng?.let { latLng ->
            Marker(
                state = MarkerState(position = latLng),
                title = "Lokasi User",
                snippet = "Ini adalah lokasi anda saat ini"
            )
        }
    }
}