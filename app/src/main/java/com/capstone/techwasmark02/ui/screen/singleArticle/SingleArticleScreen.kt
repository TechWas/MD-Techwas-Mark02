package com.capstone.techwasmark02.ui.screen.singleArticle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.data.remote.response.SingleArticleResponse
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.DefaultButton
import com.capstone.techwasmark02.ui.component.TransparentTopBar
import com.capstone.techwasmark02.ui.theme.Mist97
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun SingleArticleScreen(
    idArticle: Int,
    viewModel: SingleArticleScreenViewModel = hiltViewModel(),
) {

    LaunchedEffect(Unit) {
        viewModel.viewModelScope.launch {
            viewModel.getArticleById(idArticle)
        }
    }

    val articleResult by viewModel.articleResult.collectAsState()

    SingleArticleContent(
        articleResult = articleResult,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleArticleContent(
    articleResult: UiState<SingleArticleResponse>?,
) {

    val result = articleResult?.data?.article

    Scaffold { innerPadding ->
        val scrollState = rememberScrollState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(scrollState)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Mist97)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .clip(
                                    RoundedCornerShape(
                                        topStart = 0.dp,
                                        topEnd = 0.dp,
                                        bottomEnd = 16.dp,
                                        bottomStart = 16.dp
                                    )
                                )
                        ) {
                            Image(
                                modifier = Modifier.matchParentSize(),
                                painter = rememberAsyncImagePainter(
                                    model = result?.get(0)?.articleImageURL,
                                    placeholder = painterResource(id = R.drawable.place_holder),
                                ),
                                contentScale = ContentScale.Crop,
                                contentDescription = null
                            )
                        }

                        Column(modifier = Modifier
                            .padding(bottom = 10.dp, top = 10.dp)
                            .padding(horizontal = 16.dp)
                        ) {
                            result?.get(0)?.componentName?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            result?.get(0)?.name?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            Text(
                                text = result?.get(0)?.id.toString(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp, top = 270.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .clip(CircleShape)
                                .shadow(elevation = 4.dp, clip = true)
                                .background(Color.White)
                                .size(50.dp)
                        ) {
                            Icon(
                                Icons.Default.Favorite,
                                tint = Color.Red,
                                contentDescription = null
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    result?.get(0)?.description?.let {
                        Text(
                            text = it,
                        )
                    }

                    Spacer(modifier = Modifier.height(36.dp))

                    DefaultButton(
                        contentText = "Share",
                        onClick = {},
                        modifier = Modifier.width(150.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.7f),
                                Color.Transparent
                            ),
                            startY = 0f,
                            endY = 600f
                        )
                    )
            ) {
                TransparentTopBar(onClickNavigationIcon = { /*TODO*/ }, pageTitle = "Detail")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleArticleScreenPreview() {
    TechwasMark02Theme {
        SingleArticleContent(
            articleResult = UiState.Loading(),
        )
    }
}