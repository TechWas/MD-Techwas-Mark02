package com.capstone.techwasmark02.ui.screen.imageDetection

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.repository.PreferencesRepository
import com.capstone.techwasmark02.repository.TechwasPredictionApiRepository
import com.capstone.techwasmark02.repository.TechwasUserApiRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class ImageDetectionScreenViewModel @Inject constructor(
    private val userApiRepository: TechwasUserApiRepository,
    private val predictionApiRepository: TechwasPredictionApiRepository,
    private val preferencesRepository: PreferencesRepository
): ViewModel() {

    // permission handling
    private val _isPermissionDialogShow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isPermissionDialogShow = _isPermissionDialogShow.asStateFlow()

    fun dismissDialog() {
        _isPermissionDialogShow.value = false
    }

    fun onPermissionResult() {
        _isPermissionDialogShow.value = true
    }

    fun requestCameraPermission(context: Context, cameraPermissionLauncher: ManagedActivityResultLauncher<String, Boolean>, uri: Uri, onSuccessCallback: (Uri) -> Unit) {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) -> {
                Log.d("Zhahrany", "Permission has already granted")
                onSuccessCallback(uri)
            }
            else -> cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    // image upload & predict
    private val _imageUri: MutableStateFlow<Uri?> = MutableStateFlow(null)
    val imageUri = _imageUri.asStateFlow()

    private val _predictImageState: MutableStateFlow<UiState<String>?> = MutableStateFlow(null)
    val predictImageState = _predictImageState.asStateFlow()

    fun updateImageUri(newUri: Uri) {
        _imageUri.value = newUri
    }

//    fun predictImage(context: Context) {
//        _predictImageState.value = UiState.Loading()
//
//        val imageFileToUpload = _imageUri.value?.let { convertUriToFile(context = context, uri = it) }
//        viewModelScope.launch {
//         _predictImageState.value = imageFileToUpload?.let {
//             predictionApiRepository.predictWaste(it)
//         }
//        }
//    }

//    private fun convertUriToFile(context: Context, uri: Uri): File? {
//        val inputStream = context.contentResolver.openInputStream(uri)
//        inputStream?.let {
//            val file = createTempFile(context)
//            val outputStream = FileOutputStream(file)
//            inputStream.copyTo(outputStream)
//            outputStream.close()
//            inputStream.close()
//            return file
//        }
//        return null
//    }
//
//    private fun createTempFile(context: Context): File {
//        val directory = context.cacheDir
//        return File.createTempFile("temp", null, directory)
//    }

    private fun convertUriToFile(context: Context, uri: Uri): File? {
        val inputStream = context.contentResolver.openInputStream(uri)
        inputStream?.let {
            val file = createTempFile(context, getFileExtension(uri))
            val outputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)
            outputStream.close()
            inputStream.close()
            return file
        }
        return null
    }

    private fun createTempFile(context: Context, fileExtension: String): File {
        val directory = context.cacheDir
        return File.createTempFile("temp", ".$fileExtension", directory)
    }

    private fun getFileExtension(uri: Uri): String {
        val extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(uri.toString())
        return extension ?: "jpg" // Default to "jpg" if extension is null
    }
}