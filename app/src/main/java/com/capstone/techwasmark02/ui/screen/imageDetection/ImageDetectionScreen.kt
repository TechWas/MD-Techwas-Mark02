package com.capstone.techwasmark02.ui.screen.imageDetection

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.CameraPermissionTextProvider
import com.capstone.techwasmark02.ui.component.DefaultButton
import com.capstone.techwasmark02.ui.component.DefaultTopBar
import com.capstone.techwasmark02.ui.component.InverseButton
import com.capstone.techwasmark02.ui.component.PermissionDialog
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import com.capstone.techwasmark02.utils.ComposeFileProvider

@Composable
fun ImageDetectionScreen(viewModel: ImageDetectionScreenViewModel = hiltViewModel()) {


    val isPermissionDialogShow by viewModel.isPermissionDialogShow.collectAsState()
    val imageUri by viewModel.imageUri.collectAsState()
    val predictImageState by viewModel.predictImageState.collectAsState()

    ImageDetectionContent(
        isPermissionDialogShow = isPermissionDialogShow,
        dismissDialog = { viewModel.dismissDialog() },
        onPermissionResult = { viewModel.onPermissionResult() },
        imageUri = imageUri,
        predictImageState = predictImageState,
        updateImageUri = { viewModel.updateImageUri(it) },
        predictImage = { }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetectionContent(
    isPermissionDialogShow: Boolean,
    dismissDialog: () -> Unit,
    onPermissionResult: () -> Unit,
    imageUri: Uri?,
    predictImageState: UiState<String>?,
    updateImageUri: (Uri) -> Unit,
    predictImage: (Context) -> Unit
) {

    val context = LocalContext.current
    val uri = ComposeFileProvider.getImageUri(context)

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { storageImageUri ->
            if (storageImageUri != null) {
                updateImageUri(storageImageUri)
            }
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if(success) {
                updateImageUri(uri)
            }
        }
    )

    val cameraPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if(granted) {
                cameraLauncher.launch(uri)
            } else {
                onPermissionResult()
            }
        }
    )

    Scaffold(
        topBar = {
            DefaultTopBar(
                pageTitle = "Detect your e-waste",
                onClickNavigationIcon = {}
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 20.dp,
                            bottomStart = 20.dp
                        )
                    )
                    .background(
                        if (imageUri == null) MaterialTheme.colorScheme.primary else Color.Transparent
                    )
                    .weight(3f),
                contentAlignment = Alignment.BottomEnd
            ) {
                if (imageUri != null) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(16.dp),
                        model = imageUri,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alpha = 0.7f
                    )
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize(),
                        model = imageUri,
                        contentDescription = "Selected image",
                        contentScale = ContentScale.Fit
                    )
                }
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(120.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 20.dp,
                                bottomEnd = 20.dp
                            )
                        )
                        .background(MaterialTheme.colorScheme.tertiary)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {
                                requestCameraPermission(
                                    context = context,
                                    cameraPermissionLauncher = cameraPermissionResultLauncher,
                                    uri = uri,
                                    onSuccessCallback = {
                                        cameraLauncher.launch(it)
                                    }
                                )
                            },
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_rect_camera),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        IconButton(
                            onClick = { galleryLauncher.launch("image/*") },
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_gallery),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if(predictImageState != null) {
                    Spacer(modifier = Modifier.height(8.dp))

                    when(predictImageState) {
                        is UiState.Loading -> {
                            CircularProgressIndicator()
                        }
                        is UiState.Error -> {
                            predictImageState.message?.let { Text(text = it) }
                        }
                        is UiState.Success -> {
                            predictImageState.data?.let { Text(text = it) }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }


                DefaultButton(
                    contentText = "Detection",
                    onClick = {predictImage(context)},
                    modifier = Modifier.width(280.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                InverseButton(contentText = "Choose Type", modifier = Modifier.width(280.dp))
            }

            if (isPermissionDialogShow) {
                PermissionDialog(
                    permissionTextProvider = CameraPermissionTextProvider(),
                    isPermanentlyDeclined = !ActivityCompat.shouldShowRequestPermissionRationale(
                        context as Activity,
                        Manifest.permission.CAMERA
                    ),
                    onDismiss = { dismissDialog() },
                    onOkClick = {
                        dismissDialog()
                        requestCameraPermission(context, cameraPermissionResultLauncher, uri) {
                            cameraLauncher.launch(it)
                        }
                    },
                    onGoToAppSettingsClick = { context.openAppSettings()})
            }
        }
    }
}

private fun requestCameraPermission(context: Context, cameraPermissionLauncher: ManagedActivityResultLauncher<String, Boolean>, uri: Uri, onSuccessCallback: (Uri) -> Unit) {
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

private fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}

@Preview (showBackground = true)
@Composable
fun ImageDetectionContentPreview() {
    TechwasMark02Theme {
        ImageDetectionContent(
            imageUri = null,
            dismissDialog = {},
            onPermissionResult = {},
            isPermissionDialogShow = true,
            predictImageState = UiState.Loading(),
            updateImageUri = {},
            predictImage = {}
        )
    }
}