package com.capstone.techwasmark02.ui.screen.profileUser

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.data.local.database.entity.FavoriteArticleEntity
import com.capstone.techwasmark02.data.model.UserSession
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.data.remote.response.ForumResponse
import com.capstone.techwasmark02.data.remote.response.Token
import com.capstone.techwasmark02.data.remote.response.UserId
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.ArticleCardSmall
import com.capstone.techwasmark02.ui.component.ForumBox
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun ProfileUserScreen(
    viewModel: ProfileUserScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val userSession by viewModel.userSessionState.collectAsState()
    val bookmarkedArticleState by viewModel.bookmarkedArticleState.collectAsState()
    val favoriteArticlesList by viewModel.favoriteArticlesFlow.collectAsState(initial = null)
    val forumList by viewModel.forumList.collectAsState()

    ProfileUserContent(
        navigateToSetting = { navController.navigate(Screen.Setting.route) },
        userSession = userSession,
        bookmarkedArticleState = bookmarkedArticleState,
        favoriteArticleList = favoriteArticlesList,
        navigateToSingleArticle = { navController.navigate("${Screen.SingleArticle.route}/$it") },
        forumList = forumList,
        navigateToSingleForum = { navController.navigate("${Screen.SingleForum.route}/$it")}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileUserContent(
    navigateToSetting: () -> Unit,
    userSession: UserSession?,
    bookmarkedArticleState: UiState<ArticleResultResponse>?,
    favoriteArticleList: List<FavoriteArticleEntity>?,
    navigateToSingleArticle: (idArticle: Int) -> Unit,
    forumList: UiState<ForumResponse>?,
    navigateToSingleForum: (Int) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                title = {},
                actions = {
                    IconButton(onClick = { navigateToSetting() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_settings),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(innerPadding)
                .padding(bottom = 80.dp)
        ) {
            Box(
                modifier = Modifier
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(244.dp)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 20.dp,
                                bottomEnd = 20.dp
                            )
                        )
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(top = 20.dp),
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.TopCenter),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape),
                            painter = rememberAsyncImagePainter(
                                model = R.drawable.img_user_1,
                                placeholder = painterResource(id = R.drawable.place_holder),
                            ),
                            contentScale = ContentScale.FillHeight,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        if (userSession != null) {
                            Text(
                                text = userSession.userNameId.username,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                                ),
                                color = Color.White
                            )
                        } else {
                            Text(
                                text = "User Full Name",
                                style = MaterialTheme.typography.labelLarge,
                                color = Color.White
                            )
                        }
//                        Text(
//                            text = "user@gmail.com",
//                            style = MaterialTheme.typography.bodyMedium,
//                            color = Color.White
//                        )
                    }
                }

//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 200.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    ProfileBox(
//                        modifier = Modifier
//                            .padding(horizontal = 20.dp),
//                        navigateToSetting = navigateToSetting
//                    )
//                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(700.dp)
            ) {
                Text(
                    text = "Bookmarks",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (!favoriteArticleList.isNullOrEmpty() && favoriteArticleList.isNotEmpty()) {
                    favoriteArticleList.let {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(
                                count = favoriteArticleList.size,
                            ) { index ->
                                val article = favoriteArticleList[index]

                                ArticleCardSmall(
                                    modifier = Modifier
                                        .width(150.dp)
                                        .clickable {
                                                   navigateToSingleArticle(article.id)
                                        },
                                    imgUrl = article.articleImageURL,
                                    title = article.name,
                                    description = article.desc
                                )
                            }
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
                                text = "There's no related article",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Red
                            )
                        }
                    }
                }

//                if (bookmarkedArticleState != null) {
//                    when(bookmarkedArticleState) {
//                        is UiState.Loading -> {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(100.dp),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                CircularProgressIndicator()
//                            }
//                        }
//                        is UiState.Error -> {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(100.dp),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                Text(text = "No article to view")
//                            }
//                        }
//                        is UiState.Success -> {
//                            bookmarkedArticleState.data?.articleList?.size?.let {
//                                LazyRow(
//                                    contentPadding = PaddingValues(horizontal = 16.dp),
//                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
//                                ) {
//                                    items(
//                                        count = it,
//                                    ) { page ->
//                                        val article = bookmarkedArticleState.data.articleList[page]
//
//                                        ArticleCardSmall(
//                                            modifier = Modifier.width(150.dp),
//                                            imgUrl = article?.articleImageURL,
//                                            title = article?.name,
//                                            description = article?.desc
//                                        )
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Forum Histories",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (forumList != null) {
                    when(forumList) {
                        is UiState.Loading -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        is UiState.Error -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
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
                            val latestForumList = forumList.data?.forum

                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(400.dp)
                                    .padding(horizontal = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                contentPadding = PaddingValues( vertical = 10.dp)
                            ) {
                                if (latestForumList != null) {
                                    items(latestForumList) { forum ->
                                        ForumBox(
                                            modifier = Modifier.fillMaxWidth(),
                                            title = forum.title,
                                            desc = forum.content,
                                            place = forum.location,
                                            photoUrl = forum.imageURL,
                                            onClick = { navigateToSingleForum(forum.id) }
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
}

@Preview
@Composable
fun ProfileUserScreenPreview() {
    TechwasMark02Theme {
        ProfileUserContent(
            navigateToSetting = {},
            userSession = UserSession(
                userNameId = UserId(
                    username = "Ghina",
                    id = 1
                ),
                userLoginToken = Token(
                    accessToken = ""
                )
            ),
            bookmarkedArticleState = null,
            favoriteArticleList = null,
            navigateToSingleArticle = {},
            forumList = null,
            navigateToSingleForum = {}
        )
    }
}