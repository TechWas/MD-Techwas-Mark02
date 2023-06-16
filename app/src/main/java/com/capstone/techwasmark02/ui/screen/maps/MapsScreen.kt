package com.capstone.techwasmark02.ui.screen.maps

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.capstone.techwasmark02.ui.navigation.Screen
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

    BackHandler(true) {
        navController.navigate("${Screen.Main.route}/0")
    }

    val diy = LatLng(-7.782275587997325, 110.36709993087182)

    // posisi camera
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(diy, 12f)
    }

    // dummy marker
    val markerList = listOf(
        MapMarkerInfo(
            LatLng(-7.782589124314163, 110.38006889168956),
            "Drop Point SKE 1",
            "throw your e-waste here"
        ),
        MapMarkerInfo(
            LatLng(-7.8041473764088085, 110.39441386554735),
            "Drop Point SKE 2",
            "throw your e-waste here"
        ),
        MapMarkerInfo(
            LatLng(-7.790733483350418, 110.35766494375434),
            "Drop Point SKE 3",
            "throw your e-waste here"
        ),
        MapMarkerInfo(
            LatLng(-7.767737238844195, 110.35476371308648),
            "Drop Point SKE 4",
            "throw your e-waste here"
        ),
        MapMarkerInfo(
            LatLng(-7.774923700677788, 110.41182124955456),
            "Drop Point SKE 5",
            "throw your e-waste here"
        ),
        MapMarkerInfo(
            LatLng(-7.7798743027989685, 110.33864576493167),
            "Drop Point SKE 6",
            "throw your e-waste here"
        ),
        MapMarkerInfo(
            LatLng(-7.783068208639076, 110.3805524301342),
            "Drop Point SKE 7",
            "throw your e-waste here"
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
            // cari marker dengan jarak terdekat
            var closestMarker: MapMarkerInfo? = null
            var closestDistance = Float.MAX_VALUE
            userLatLng?.let { userLocation ->
                for (marker in markerList) {
                    val distanceResults = FloatArray(1)
                    Location.distanceBetween(userLocation.latitude, userLocation.longitude, marker.position.latitude, marker.position.longitude, distanceResults)
                    val distance = distanceResults[0]
                    if (distance < closestDistance) {
                        closestDistance = distance
                        closestMarker = marker
                    }
                }
            }

        // kasih cat marker nya bg
        markerList.forEach { marker ->
            val markerIcon = if (marker == closestMarker) {
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            } else {
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
            }

            Marker(
                state = MarkerState(
                    position = marker.position,
                ),
                icon = markerIcon,
                title = marker.title,
                snippet = marker.snippet
            )
        }

        userLatLng?.let { latLng ->
            val maxDistance = 5000f // jarak maksimum user ke lokasi terdekat (meter)
            if (closestDistance > maxDistance) {
                AlertDialog(
                    onDismissRequest = { },
                    title = {
                        Text(
                            text = "oh no, your location is too far",
                            style = MaterialTheme.typography.labelLarge
                        ) },
                    text = {
                        Text(
                            text = "you are too far from the drop point",
                            style = MaterialTheme.typography.bodyMedium
                        ) },
                    confirmButton = {
                        Button(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Text("Back")
                        }
                    },
                )
            }

            Marker(
                state = MarkerState(position = latLng),
                title = "Your Location",
            )
        }
    }
}