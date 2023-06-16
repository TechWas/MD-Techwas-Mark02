package com.capstone.techwasmark02.ui.screen.forumCreate

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.capstone.techwasmark02.data.model.ForumToCreateInfo
import com.capstone.techwasmark02.data.remote.response.CreateForumResponse
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

    ForumCreateContent(
        navigateBackToForum = {navController.navigate("${Screen.Main.route}/1")},
        forumToCreateInfo = forumToCreateInfo,
        updateForumToCreateInfo = { viewModel.updateForumToCreateInfo(it) },
        createForumState = createForumState,
        createNewForum = { viewModel.createNewForum() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumCreateContent(
    navigateBackToForum: () -> Unit,
    forumToCreateInfo: ForumToCreateInfo,
    updateForumToCreateInfo: (ForumToCreateInfo) -> Unit,
    createForumState: UiState<CreateForumResponse>?,
    createNewForum: () -> Unit
) {

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

    LaunchedEffect(key1 = selectedFilter) {
        selectedFilter.type.let {
            forumToCreateInfo.copy(
                category = it
            )
        }.let { updateForumToCreateInfo(it) }
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
                        .background(Color.LightGray)
                ) {

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
                    val context = LocalContext.current

                    if (createForumState != null) {
                        when(createForumState) {
                            is UiState.Error -> {
                                Toast.makeText(context, "Fail to create new forum", Toast.LENGTH_SHORT).show()
                            }
                            is UiState.Loading -> {
                                CircularProgressIndicator()
                            }
                            is UiState.Success -> {
                                navigateBackToForum()
                            }
                        }
                    } else {
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
            createNewForum = {}
        )
    }
}