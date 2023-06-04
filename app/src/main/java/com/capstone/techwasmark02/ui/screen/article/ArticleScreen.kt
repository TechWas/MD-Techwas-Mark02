package com.capstone.techwasmark02.ui.screen.article

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.ArticleCardSmall
import com.capstone.techwasmark02.ui.component.DefaultBottomBar
import com.capstone.techwasmark02.ui.component.SearchBox
import com.capstone.techwasmark02.ui.component.SelectableText
import com.capstone.techwasmark02.ui.componentType.ArticleFilterType
import com.capstone.techwasmark02.ui.componentType.BottomBarItemType
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun ArticleScreen(
    viewModel: ArticleScreenViewModel = hiltViewModel()
) {
    val articleList by viewModel.articleList.collectAsState()

    ArticleContent(
        articleList = articleList,
    )
}

@Composable
fun ArticleContent(
    articleList: UiState<ArticleResultResponse>?
) {

    var inputValue by remember {
        mutableStateOf("")
    }

    val filterTypeList = listOf(
        ArticleFilterType.General,
        ArticleFilterType.Battery,
        ArticleFilterType.Laptop
    )

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
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
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(
                        items = filterTypeList,
                    ) { item ->
                        SelectableText(
                            filterType = item,
                            selected = true
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (articleList != null) {
                    when (articleList) {
                        is UiState.Loading -> {
                            CircularProgressIndicator()
                        }
                        is UiState.Error -> {
                            articleList.message?.let {
                                Text(text = it)
                            }
                        }
                        is UiState.Success -> {
                            val componentListArticle = articleList.data?.componentList
                            if(componentListArticle != null) {
                                LazyVerticalGrid(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(600.dp),
                                    columns = GridCells.Fixed(2),
                                    contentPadding = PaddingValues(vertical = 8.dp),
                                    verticalArrangement = Arrangement.spacedBy(16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                ) {
                                    items(componentListArticle) { item ->
                                        ArticleCardSmall(
                                            imgUrl = item?.articleImageURL,
                                            title = item?.name,
                                            description = item?.desc
                                        )
                                    }
                                }
                            }

                        }
                    }

                } else {
                   // jika null
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        DefaultBottomBar(selectedType = BottomBarItemType.Article)
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    TechwasMark02Theme {
        ArticleScreen()
    }
}
