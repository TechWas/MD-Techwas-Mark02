package com.capstone.techwasmark02.ui.screen.camera

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.data.remote.response.DetectionResultResponse
import com.capstone.techwasmark02.repository.TechwasPredictionApiRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class CameraScreenViewModel @Inject constructor(
    private val predictionApiRepository: TechwasPredictionApiRepository
): ViewModel() {

    private val _photoUri: MutableStateFlow<Uri?> = MutableStateFlow(null)
    val photoUri = _photoUri.asStateFlow()

    private val _predictImageState: MutableStateFlow<UiState<DetectionResultResponse>?> = MutableStateFlow(null)
    val predictImageState = _predictImageState.asStateFlow()

    fun updatePhotoUri(newUri: Uri) {
        _photoUri.value = newUri
    }

    fun predictImage(context: Context) {
        _predictImageState.value = UiState.Loading()

        val imageFileToUpload = _photoUri.value?.let { convertUriToFile(context = context, uri = it) }
        viewModelScope.launch {
            _predictImageState.value = imageFileToUpload?.let {
                predictionApiRepository.predictWaste(it)
            }
        }
    }

    fun onSuccessPredictImage() {
        _predictImageState.value = null
    }

}

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