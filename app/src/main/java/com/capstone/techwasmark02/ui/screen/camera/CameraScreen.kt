package com.capstone.techwasmark02.ui.screen.camera

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.capstone.techwasmark02.data.remote.response.DetectionResultResponse
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.CameraView
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.data.remote.response.Predictions
import com.capstone.techwasmark02.data.remote.response.ResultComponentType
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import java.io.File
import java.util.concurrent.Executors
import kotlin.reflect.full.memberProperties

@Composable
fun CameraScreen(
    viewModel: CameraScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val photoUri by viewModel.photoUri.collectAsState()
    val predictImageState by viewModel.predictImageState.collectAsState()

    CameraContent(
        photoUri = photoUri,
        predictImageState = predictImageState,
        updatePhotoUri =  { viewModel.updatePhotoUri(it) },
        predictImage = { viewModel.predictImage(it)},
        navigateToResult = { uri, result ->
            navController.navigate("detectionResult/$uri/$result")
        }
    ) { viewModel.onSuccessPredictImage() }
}

@Composable
fun CameraContent(
    photoUri: Uri?,
    predictImageState: UiState<DetectionResultResponse>?,
    updatePhotoUri: (Uri) -> Unit,
    predictImage: (Context) -> Unit,
    navigateToResult: (uri: String, result: String) -> Unit,
    onSuccessPredictImage:() -> Unit
) {

    val context = LocalContext.current
    val outputDirectory = getOutputDirectory(context)
    val cameraExecutor = Executors.newSingleThreadExecutor()

    var imageIsTaken by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = imageIsTaken) {
        if (imageIsTaken) {
            predictImage(context)
        }
    }

    LaunchedEffect(key1 = predictImageState) {
        if (predictImageState != null) {
            when (predictImageState) {
                is UiState.Success -> {
//                    val predictResult = predictImageState.data?.predictions?.laptop.toString()
//                    Toast.makeText(context,"Result: $predictResult", Toast.LENGTH_SHORT).show()
                    val predictResult = predictImageState.data?.predictions?.let {
                        getAndSortResult(
                            it
                        )
                    }

                    val stringUri = Uri.encode(photoUri.toString())
                    onSuccessPredictImage()
                    if (predictResult != null) {
                        navigateToResult(stringUri, predictResult)
                    }
                }
                is UiState.Error -> {
                    val predictResult = predictImageState.message
                    Toast.makeText(context,"$predictResult", Toast.LENGTH_SHORT).show()
                    imageIsTaken = false
                }
                is UiState.Loading -> {
                    Toast.makeText(context,"Your e-waste is being predicted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CameraView(
            outputDirectory = outputDirectory,
            executor = cameraExecutor,
            onImageCaptured = { uri ->
                updatePhotoUri(uri)
                imageIsTaken = true
            },
            onError = { Log.e("zhahrany", "View error:", it)}
        )

        if (predictImageState != null) {
            when (predictImageState) {
                is UiState.Success, is UiState.Error -> {
                    // do nothing
                }
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .alpha(0.3f)
                                .background(Color.Black),
                        ) {}

                        CircularProgressIndicator(
                            color = Color.White
                        )
                    }
                }
            }
        }
    }

}

private fun getOutputDirectory(context: Context): File {
    val cacheDir = context.externalCacheDirs.firstOrNull()?.let {
        File(it, context.resources.getString(R.string.app_name)).apply { mkdirs() }
    }

    return if (cacheDir != null && cacheDir.exists()) cacheDir else context.filesDir
}

private fun getAndSortResult(detectionResult: Predictions): String {
    val resultComponentList = emptyList<ResultComponentType>().toMutableList()
    for (property in Predictions::class.memberProperties) {
        if (property.get(detectionResult) != null) {
            val resultComponentItem = ResultComponentType(
                name = property.name,
                percentage = property.get(detectionResult).toString().toDouble()
            )
            resultComponentList.add(resultComponentItem)
        }
    }

    val sortedResultComponentList = resultComponentList.sortedWith(compareBy({it.percentage})).reversed()

    val stringResultList = emptyList<String>().toMutableList()

    sortedResultComponentList.forEach { componentItem ->
        val stringResultItem = "${componentItem.name}: ${componentItem.percentage}"
        stringResultList.add(stringResultItem)
    }

    return stringResultList.joinToString(separator = ",")

}

@Preview(showBackground = true)
@Composable
fun CameraScreenPreview() {
    TechwasMark02Theme() {
        CameraContent(
            photoUri = null,
            updatePhotoUri = {},
            navigateToResult = { uri, result ->  },
            predictImage = {},
            predictImageState = null
        ) {}
    }
}