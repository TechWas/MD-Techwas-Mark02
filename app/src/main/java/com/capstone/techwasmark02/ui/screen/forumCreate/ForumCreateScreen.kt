package com.capstone.techwasmark02.ui.screen.forumCreate

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.data.model.ForumToCreateInfo
import com.capstone.techwasmark02.data.remote.response.CreateForumResponse
import com.capstone.techwasmark02.data.remote.response.ImageUrlResponse
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.DefaultTopBar
import com.capstone.techwasmark02.ui.component.SelectableText
import com.capstone.techwasmark02.ui.componentType.ArticleFilterType
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun ForumCreateScreen(
    navController: NavHostController,
    viewModel: ForumCreateScreenViewModel = hiltViewModel()
) {

    val forumToCreateInfo by viewModel.forumToCreateInfo.collectAsState()
    val createForumState by viewModel.createForumState.collectAsState()
    val imageUri by viewModel.imageUri.collectAsState()
    val uploadAndGetImageUrlState by viewModel.uploadAndGetImageUrlState.collectAsState()

    ForumCreateContent(
        navigateBackToForum = {navController.navigate("${Screen.Main.route}/1")},
        forumToCreateInfo = forumToCreateInfo,
        updateForumToCreateInfo = { viewModel.updateForumToCreateInfo(it) },
        createForumState = createForumState,
        createNewForum = { viewModel.createNewForum() },
        imageUri = imageUri,
        updateImageUri = { viewModel.updateImageUri(it) },
        uploadAndGetImageUrl = { viewModel.uploadAndGetImageUrl(it)},
        uploadAndGetImageUrlState = uploadAndGetImageUrlState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumCreateContent(
    navigateBackToForum: () -> Unit,
    forumToCreateInfo: ForumToCreateInfo,
    updateForumToCreateInfo: (ForumToCreateInfo) -> Unit,
    createForumState: UiState<CreateForumResponse>?,
    createNewForum: () -> Unit,
    imageUri: Uri?,
    updateImageUri: (Uri) -> Unit,
    uploadAndGetImageUrl: (Context) -> Unit,
    uploadAndGetImageUrlState: UiState<ImageUrlResponse>?
) {

    val context = LocalContext.current

    val filterTypeList = listOf(
        ArticleFilterType.Battery,
        ArticleFilterType.Cable,
        ArticleFilterType.CrtTv,
        ArticleFilterType.EKettle,
        ArticleFilterType.Refrigerator,
        ArticleFilterType.Keyboard,
        ArticleFilterType.Laptop,
        ArticleFilterType.LightBulb,
        ArticleFilterType.Monitor,
        ArticleFilterType.Mouse,
        ArticleFilterType.PCB,
        ArticleFilterType.Printer,
        ArticleFilterType.RiceCooker,
        ArticleFilterType.WashingMachine,
        ArticleFilterType.Phone
    )

    var selectedFilter by remember {
        mutableStateOf(filterTypeList.first())
    }

    val scrollState = rememberScrollState()

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { storageImageUri ->
            if (storageImageUri != null) {
                updateImageUri(storageImageUri)
            }
        }
    )


    LaunchedEffect(key1 = selectedFilter) {
        selectedFilter.type.let {
            forumToCreateInfo.copy(
                category = it
            )
        }.let { updateForumToCreateInfo(it) }
    }

    LaunchedEffect(key1 = imageUri) {
        if (imageUri != null) {
            uploadAndGetImageUrl(context)
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 88.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "E-waste picture",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    if (uploadAndGetImageUrlState != null) {
                        when(uploadAndGetImageUrlState) {
                            is UiState.Error -> {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .border(
                                                width = 1.dp,
                                                color = Color.Red,
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                            .padding(8.dp)

                                    ) {
                                        Text(
                                            text = "Fail to fetch forum",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = Color.Red
                                        )
                                    }
                                }
                            }
                            is UiState.Loading -> {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                            is UiState.Success -> {
                                if (imageUri != null) {
                                    AsyncImage(
                                        model = imageUri,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                uploadAndGetImageUrlState.data?.imgURL?.let {
                                    forumToCreateInfo.copy(
                                        imageUrl = it
                                    )
                                }?.let { updateForumToCreateInfo(it) }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(end = 10.dp, bottom = 10.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.tertiary)
                                .clickable {
                                    galleryLauncher.launch("image/*")
                                }
                                .padding(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_gallery_big),
                                contentDescription = null,
                                tint = Color.Black.copy(alpha = 0.7f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "E-waste category",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                )
            }

            LazyRow(
                modifier = Modifier.height(48.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(
                    items = filterTypeList,
                ) { item ->
                    SelectableText(
                        filterType = item,
                        selected = item == selectedFilter,
                        modifier = Modifier,
                        onClick = {
                            selectedFilter = item
                        }
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 80.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Forum title",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                TextField(
                    value = forumToCreateInfo.title,
                    onValueChange = { newValue ->
                        updateForumToCreateInfo(forumToCreateInfo.copy(
                            title = newValue
                        ))
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Location",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                TextField(
                    value = forumToCreateInfo.location,
                    onValueChange = { newValue ->
                        updateForumToCreateInfo(forumToCreateInfo.copy(
                            location = newValue
                        ))
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Forum Description",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                TextField(
                    value = forumToCreateInfo.content,
                    onValueChange = { newValue ->
                        updateForumToCreateInfo(forumToCreateInfo.copy(
                            content = newValue
                        ))
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            DefaultTopBar(onClickNavigationIcon = navigateBackToForum, pageTitle = "Create New Forum")

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                val context = LocalContext.current

                if (createForumState != null) {

                    when(createForumState) {
                        is UiState.Error -> {
                            Toast.makeText(context, "Fail to create new forum", Toast.LENGTH_SHORT).show()
                        }
                        is UiState.Loading -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        is UiState.Success -> {
                            navigateBackToForum()
                        }
                    }
                } else {
                    ElevatedButton(
                        onClick = createNewForum ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                    ) {
                        Text(
                            text = "Create Forum",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun ForumCreateScreenPreview() {
    TechwasMark02Theme {
        ForumCreateContent(
            navigateBackToForum = {},
            forumToCreateInfo = ForumToCreateInfo(
                category = "",
                content = "",
                imageUrl = "",
                location = "",
                title = ""
            ),
            updateForumToCreateInfo = {},
            createForumState = null,
            createNewForum = {},
            imageUri = null,
            updateImageUri = {},
            uploadAndGetImageUrlState = null,
            uploadAndGetImageUrl = {}
        )
    }
}