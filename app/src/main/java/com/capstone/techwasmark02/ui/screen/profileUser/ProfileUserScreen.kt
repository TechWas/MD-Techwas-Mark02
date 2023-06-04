package com.capstone.techwasmark02.ui.screen.profileUser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.component.ArticleCardBig
import com.capstone.techwasmark02.ui.component.DefaultBottomBar
import com.capstone.techwasmark02.ui.component.DefaultTopBar
import com.capstone.techwasmark02.ui.component.ForumBox
import com.capstone.techwasmark02.ui.component.ProfileBox
import com.capstone.techwasmark02.ui.componentType.BottomBarItemType
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import kotlin.random.Random

@Composable
fun ProfileUserScreen(
    navController: NavHostController
) {
    ProfileUserContent(
        navigateToHome = { navController.navigate(Screen.Home.route) },
        navigateToArticle = { navController.navigate(Screen.Article.route) },
        navigateToForum = { navController.navigate(Screen.Forum.route) },
        navigateToProfile = { navController.navigate(Screen.Profile.route) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileUserContent(
    navigateToHome: () -> Unit,
    navigateToForum: () -> Unit,
    navigateToArticle: () -> Unit,
    navigateToProfile: () -> Unit
) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                pageTitle = "Profile",
                onClickNavigationIcon = {}
            )
        },
        bottomBar = {
            DefaultBottomBar(
                selectedType = BottomBarItemType.Profile,
                navigateToProfile = navigateToProfile,
                navigateToForum = navigateToForum,
                navigateToArticle = navigateToArticle,
                navigateToHome = navigateToHome
            )
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(innerPadding)
                .padding(bottom = 20.dp)
        ) {
            Box(
                modifier = Modifier
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(236.dp)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 20.dp,
                                bottomEnd = 20.dp
                            )
                        )
                        .background(MaterialTheme.colorScheme.primary),
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
                                model = "https://picsum.photos/seed/${Random.nextInt()}/320/120",
                                placeholder = painterResource(id = R.drawable.place_holder),
                            ),
                            contentScale = ContentScale.FillHeight,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "User Full Name",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White
                        )
                        Text(
                            text = "user@gmail.com",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 200.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfileBox(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
            ) {
                Text(
                    text = "Bookmarks",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(
                        count = 10,
                    ) { item ->
                        ArticleCardBig(
                            modifier = Modifier
                                .width(150.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Forum Histories",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(count = 5) {
                        ForumBox(modifier = Modifier
                            .padding(horizontal = 16.dp)
                        )
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
            navigateToHome = {},
            navigateToArticle = {},
            navigateToForum = {},
            navigateToProfile = {}
        )
    }
}