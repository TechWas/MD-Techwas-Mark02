package com.capstone.techwasmark02.ui.screen.forumCreate

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.common.Resource
import com.capstone.techwasmark02.data.model.ForumToCreateInfo
import com.capstone.techwasmark02.data.model.UserSession
import com.capstone.techwasmark02.data.remote.response.CreateForumResponse
import com.capstone.techwasmark02.data.remote.response.ImageUrlResponse
import com.capstone.techwasmark02.data.remote.response.Token
import com.capstone.techwasmark02.data.remote.response.UserId
import com.capstone.techwasmark02.repository.PreferencesRepository
import com.capstone.techwasmark02.repository.TechwasForumApiRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class ForumCreateScreenViewModel @Inject constructor(
    private val forumApiRepository: TechwasForumApiRepository,
    private val preferencesRepository: PreferencesRepository
): ViewModel() {

    private val _userSessionState: MutableStateFlow<UserSession?> = MutableStateFlow(null)
    val userSessionState = _userSessionState.asStateFlow()

    private val _createForumState: MutableStateFlow<UiState<CreateForumResponse>?> = MutableStateFlow(null)
    val createForumState = _createForumState.asStateFlow()

    private val _forumToCreateInfo: MutableStateFlow<ForumToCreateInfo> = MutableStateFlow(
        ForumToCreateInfo(
            category = "Mouse",
            content = "",
            imageUrl = "",
            location = "",
            title = ""
        )
    )
    val forumToCreateInfo = _forumToCreateInfo.asStateFlow()

    private val _imageUri: MutableStateFlow<Uri?> = MutableStateFlow(null)
    val imageUri = _imageUri.asStateFlow()

    private val _uploadAndGetImageUrlState: MutableStateFlow<UiState<ImageUrlResponse>?> = MutableStateFlow(null)
    val uploadAndGetImageUrlState = _uploadAndGetImageUrlState.asStateFlow()

    fun updateForumToCreateInfo(forumToCreateInfo: ForumToCreateInfo) {
        _forumToCreateInfo.value = forumToCreateInfo
    }

    fun updateImageUri(newUri: Uri) {
        _imageUri.value = newUri
    }

    fun createNewForum() {
        _createForumState.value = UiState.Loading()
        viewModelScope.launch {
            _createForumState.value = _userSessionState.value?.userLoginToken?.accessToken?.let {
                forumApiRepository.createNewForum(
                    forumToCreateInfo = _forumToCreateInfo.value,
                    userToken = it
                )
            }
        }
    }

    fun uploadAndGetImageUrl(context: Context) {
        val fileToUpload = _imageUri.value?.let { convertUriToFile(context, it) }

        _uploadAndGetImageUrlState.value = UiState.Loading()
        viewModelScope.launch {
            _uploadAndGetImageUrlState.value = fileToUpload?.let {
                _userSessionState.value?.userLoginToken?.accessToken?.let { it1 ->
                    forumApiRepository.uploadAndGetImageUrl(
                        file = it,
                        userToken = it1
                    )
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            val result = preferencesRepository.getActiveSession()
            when(result) {
                is Resource.Error -> {
                    _userSessionState.value = UserSession(
                        userLoginToken = Token(accessToken = ""),
                        userNameId = UserId(username = "", id = 0)
                    )
                }
                is Resource.Success -> {
                    _userSessionState.value = result.data
                }
            }
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

}