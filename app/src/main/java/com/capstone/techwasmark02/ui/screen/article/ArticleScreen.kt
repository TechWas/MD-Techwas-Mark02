package com.capstone.techwasmark02.ui.screen.article

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.ArticleCardSmall
import com.capstone.techwasmark02.ui.component.SearchBox
import com.capstone.techwasmark02.ui.component.SelectableText
import com.capstone.techwasmark02.ui.componentType.ArticleFilterType
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun ArticleScreen(
    viewModel: ArticleScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val articleList by viewModel.articleList.collectAsState()

    ArticleContent(
        viewModel = viewModel,
        articleList = articleList,
        navigateToSingleArticle = { navController.navigate("${Screen.SingleArticle.route}/$it") },
    )
}

@Composable
fun ArticleContent(
    viewModel: ArticleScreenViewModel,
    articleList: UiState<ArticleResultResponse>?,
    navigateToSingleArticle: (idArticle: Int) -> Unit,
) {

    var inputValue by remember {
        mutableStateOf("")
    }

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
                .padding(horizontal = 16.dp)
                .padding(top = 20.dp)
        ) {
            Text(
                text = "Articles",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            SearchBox(
                value = inputValue,
                onValueChange = { newValue ->
                    inputValue = newValue
                    if(inputValue.isEmpty()) {
                        viewModel.getAllFilterArticle(selectedFilter?.id ?: 0)
                    } else {
                        viewModel.getArticleByName(inputValue, selectedFilter ?: ArticleFilterType.General)
                    }
                },
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                modifier = Modifier.height(48.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(
                    items = filterTypeList,
                ) { item ->
                    IconButton(
                        modifier = Modifier.requiredWidthIn(min = 60.dp),
                        onClick = {
                            selectedFilter = item
                            viewModel.getAllFilterArticle(item.id)
                        },
                        enabled = item != selectedFilter,
                        content = {
                            SelectableText(
                                filterType = item,
                                selected = item == selectedFilter,
//                                modifier = Modifier.clickable(enabled = item != selectedFilter) {
//                                    selectedFilter = item
//                                    viewModel.getAllFilterArticle(item.id)
//                                }
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (articleList != null) {
                when (articleList) {
                    is UiState.Loading -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    is UiState.Error -> {
                        articleList.message?.let {
                            Text(text = it)
                        }
                    }
                    is UiState.Success -> {
                        val componentListArticle = articleList.data?.articleList
                        if(!componentListArticle.isNullOrEmpty()) {
                            LazyVerticalGrid(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(bottom = 20.dp),
                                columns = GridCells.Fixed(2),
                                contentPadding = PaddingValues(vertical = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                            ) {
                                items(componentListArticle) { item ->
                                    ArticleCardSmall(
                                        modifier = Modifier
                                            .width(150.dp)
                                            .clickable {
                                                item?.id?.let { navigateToSingleArticle(it) }
                                            },
                                        imgUrl = item?.articleImageURL,
                                        title = item?.name,
                                        description = item?.desc,
                                    )
                                }
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp),
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
                                        text = "There's no related article",
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

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    TechwasMark02Theme {
//        ArticleContent(
//            articleList = UiState.Loading(),
//            navigateToHome = {},
//            navigateToArticle = {},
//            navigateToForum = {},
//            navigateToProfile = {}
//        )
    }
}
