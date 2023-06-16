package com.capstone.techwasmark02.ui.screen.forum

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.data.remote.response.Forum
import com.capstone.techwasmark02.data.remote.response.ForumResponse
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.ForumBox
import com.capstone.techwasmark02.ui.component.SearchBox
import com.capstone.techwasmark02.ui.component.SelectableText
import com.capstone.techwasmark02.ui.componentType.ArticleFilterType
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun ForumScreen(
    navController: NavHostController,
    viewModel: ForumScreenViewModel = hiltViewModel()
) {
    val forumListState by viewModel.forumList.collectAsState()
    val searchBoxValue by viewModel.searchBoxValue.collectAsState()

    ForumContent(
        navigateToSingleForum = { navController.navigate("${Screen.SingleForum.route}/$it")},
        forumListState = forumListState,
        navigateToCreateForum = { navController.navigate(Screen.CreateForum.route)},
        searchBoxValue = searchBoxValue,
        onSearchBoxValueChange = { viewModel.updateSearchBoxValue(it)}
    )
}

@Composable
fun ForumContent(
    navigateToSingleForum: (Int) -> Unit,
    forumListState: UiState<ForumResponse>?,
    navigateToCreateForum: () -> Unit,
    searchBoxValue: String,
    onSearchBoxValueChange: (String) -> Unit
) {

    val filterTypeList = listOf(
        ArticleFilterType.General,
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
        mutableStateOf(filterTypeList.firstOrNull())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 80.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier
                            .width(280.dp)
                    ) {
                        Text(
                            text = "Forum",
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Share your thoughts and connect with others",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp, end = 8.dp)
                    ) {
                        IconButton(
                            onClick = navigateToCreateForum,
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_create),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                SearchBox(
                    value = searchBoxValue,
                    onValueChange = onSearchBoxValueChange,
                )

                Spacer(modifier = Modifier.height(16.dp))
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

            if (forumListState != null) {
                when(forumListState) {
                    is UiState.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    is UiState.Error -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(175.dp),
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
                    is UiState.Success -> {
                        val currentForumList = forumListState.data?.forum

                        var filteredForumList by remember {
                            mutableStateOf(currentForumList)
                        }

                        LaunchedEffect(key1 = searchBoxValue, key2 = selectedFilter) {
                            if (currentForumList != null) {
                                filteredForumList = selectedFilter?.type?.let {
                                    searchForum(
                                        forumList = currentForumList,
                                        searchBoxValue = searchBoxValue,
                                        selectedFilter = it
                                    )
                                }
                            }
                        }

                        if (!filteredForumList.isNullOrEmpty() && filteredForumList!!.isNotEmpty()) {
                            LazyColumn(
                                modifier = Modifier,
                                contentPadding = PaddingValues(vertical = 20.dp, horizontal = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                items(filteredForumList!!) { forum ->
                                    ForumBox(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        title = forum.title,
                                        place = forum.location,
                                        desc = forum.content,
                                        onClick = { navigateToSingleForum(forum.id) },
                                        photoUrl = forum.imageURL
                                    )
                                }
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(175.dp),
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
                                        text = "No forum to view",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.Red
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}

private fun searchForum(forumList: List<Forum>, searchBoxValue: String, selectedFilter: String): List<Forum> {
    if (searchBoxValue == "" && selectedFilter == "General") {
        return forumList
    }

    val searchBoxFilter = forumList.filter { forum ->
        forum.title.contains(searchBoxValue, ignoreCase = true)
    }

    val selectedFilterList = searchBoxFilter.filter { forum ->
        forum.category == selectedFilter
    }

    return selectedFilterList
}

@Preview
@Composable
fun ForumScreenPreview() {
    TechwasMark02Theme {
        ForumContent(
            navigateToSingleForum = {},
            forumListState = null,
            navigateToCreateForum = {},
            searchBoxValue = "",
            onSearchBoxValueChange = {}
        )
    }
}