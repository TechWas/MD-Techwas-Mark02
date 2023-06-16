package com.capstone.techwasmark02.ui.screen.singleArticle

import android.content.Intent
import android.text.Html
import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.data.local.database.entity.FavoriteArticleEntity
import com.capstone.techwasmark02.data.model.FavoriteArticle
import com.capstone.techwasmark02.data.remote.response.SingleArticleResponse
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.DefaultButton
import com.capstone.techwasmark02.ui.component.HtmlText
import com.capstone.techwasmark02.ui.component.TransparentTopBar
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.theme.Mist97
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import kotlinx.coroutines.launch

@Composable
fun SingleArticleScreen(
    idArticle: Int,
    viewModel: SingleArticleScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {

    LaunchedEffect(Unit) {
        viewModel.viewModelScope.launch {
            viewModel.getArticleById(idArticle)
            viewModel.getFavArticleById(idArticle)
        }
    }

    val articleResult by viewModel.articleResult.collectAsState()

    val isArticleFavorited by viewModel.isArticleFavorited.collectAsState(initial = null)

    SingleArticleContent(
        articleResult = articleResult,
        navigateToArticle = { navController.navigate("${Screen.Main.route}/2") },
        isArticleFavorited = isArticleFavorited,
        updateArticleFavorited = { articleGetFavorited, favoriteArticle ->  viewModel.updateArticleFavorited(articleGetFavorited, favoriteArticle)}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleArticleContent(
    articleResult: UiState<SingleArticleResponse>?,
    navigateToArticle: () -> Unit,
    isArticleFavorited: FavoriteArticleEntity?,
    updateArticleFavorited: ((articleGetFavorited: Boolean, favoriteArticle: FavoriteArticle) -> Unit)
) {
    val context = LocalContext.current

    BackHandler(true) {
        navigateToArticle()
    }

    val result = articleResult?.data?.article

    Scaffold { innerPadding ->
        val scrollState = rememberScrollState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (articleResult != null) {
                when(articleResult) {
                    is UiState.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    is UiState.Error -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    is UiState.Success -> {
                        val currentArticle = articleResult.data?.article?.get(0)
                        val isArticleFavorite = currentArticle?.id == isArticleFavorited?.id

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
                                            text = "source: techwaste",
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
                                        onClick = {
                                            val favoritedArticle = currentArticle?.id?.let {
                                                currentArticle.name?.let { it1 ->
                                                    currentArticle.articleImageURL?.let { it2 ->
                                                        currentArticle.componentID?.let { it3 ->
                                                            currentArticle.description?.let { it4 ->
                                                                FavoriteArticle(
                                                                    id = it,
                                                                    name = it1,
                                                                    imageURL = it2,
                                                                    compId = it3,
                                                                    desc = it4
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            if (isArticleFavorite) {
                                                if (favoritedArticle != null) {
                                                    updateArticleFavorited(false, favoritedArticle)
                                                }
                                            } else {
                                                if (favoritedArticle != null) {
                                                    updateArticleFavorited(true, favoritedArticle)
                                                }
                                            }
                                        },
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .shadow(elevation = 4.dp, clip = true)
                                            .background(Color.White)
                                            .size(50.dp)
                                    ) {
                                        Icon(
                                            Icons.Default.Favorite,
                                            tint = if (isArticleFavorite) Color.Red else Color.LightGray,
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
                                    HtmlText(
                                        html = it,
                                        textStyle = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color.Black,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp
                                        )
                                    )
                                }

                                Spacer(modifier = Modifier.height(36.dp))

                                val plainText = Html.fromHtml(result?.get(0)?.description).toString()

                                DefaultButton(
                                    contentText = "Share",
                                    onClick = {
                                        val intent = Intent(Intent.ACTION_SEND)
                                        intent.type = "text/plain"
                                        intent.putExtra(Intent.EXTRA_SUBJECT, result?.get(0)?.name)
                                        intent.putExtra(Intent.EXTRA_TEXT,
                                            "$plainText\nSource: Techwaste"
                                        )
                                        context.startActivity(intent)
                                    },
                                    modifier = Modifier.width(150.dp)
                                )
                            }
                        }
                    }
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
                TransparentTopBar(onClickNavigationIcon = { navigateToArticle() }, pageTitle = "Detail")
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
            navigateToArticle = {},
            isArticleFavorited = null,
            updateArticleFavorited = fun(articleGetFavorited: Boolean,
                                         favoriteArticle: FavoriteArticle) {}
        )
    }
}