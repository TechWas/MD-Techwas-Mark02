package com.capstone.techwasmark02.ui.screen.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.data.remote.response.ArticleList
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.ArticleCardBig
import com.capstone.techwasmark02.ui.component.FeatureBox
import com.capstone.techwasmark02.ui.component.FeatureBoxLarge
import com.capstone.techwasmark02.ui.componentType.FeatureBoxType
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.navigation.Screen.Article
import com.capstone.techwasmark02.ui.navigation.Screen.Camera
import com.capstone.techwasmark02.ui.navigation.Screen.Catalog
import com.capstone.techwasmark02.ui.navigation.Screen.Forum
import com.capstone.techwasmark02.ui.navigation.Screen.Home
import com.capstone.techwasmark02.ui.navigation.Screen.Maps
import com.capstone.techwasmark02.ui.navigation.Screen.Profile
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import com.capstone.techwasmark02.ui.theme.yellow
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val latestArticleState by viewModel.latestArticleState.collectAsState()

    HomeContent(
        navigateToCamera = { navController.navigate(Camera.route) },
        navigateToHome = { navController.navigate(Home.route) },
        navigateToArticle = { navController.navigate(Article.route) },
        navigateToForum = { navController.navigate(Forum.route) },
        navigateToProfile = { navController.navigate(Profile.route) },
        navigateToCatalog = { navController.navigate(Catalog.route) },
        navigateToMaps = { navController.navigate(Maps.route) },
        navigateToSingleArticle = { navController.navigate("${Screen.SingleArticle.route}/$it") },
        latestArticleState = latestArticleState
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    navigateToCamera: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToForum: () -> Unit,
    navigateToArticle: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToCatalog: () -> Unit,
    navigateToMaps: () -> Unit,
    navigateToSingleArticle: (idArticle: Int) -> Unit,
    latestArticleState: UiState<ArticleResultResponse>?
) {

    val context = LocalContext.current

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if(granted) {
                navigateToCamera()
            }
        }
    )


    Scaffold(
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        val titlePaddingBottom = 10.dp
        val titlePaddingTop = 24.dp

        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(top = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight(),

                            ) {
                            Image(
                                modifier = Modifier.size(24.dp, 27.dp),
                                painter = painterResource(id = R.drawable.logo_techwaste),
                                contentDescription = null
                            )
                        }
                        Text(
                            text = "Techwas",
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .offset(y = 5.dp),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(
                        onClick = { },
//                        modifier = Modifier.size(21.dp, 24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_nofitications),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(
                            RoundedCornerShape(
                                topStart = 20.dp,
                                topEnd = 20.dp
                            )
                        )
                        .background(Color.White)
                        .padding(bottom = 90.dp)
                ) {

                    Spacer(modifier = Modifier.height(18.dp))

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Features",
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }

                        Spacer(modifier = Modifier.height(titlePaddingBottom))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            FeatureBoxLarge(
                                featureBoxType = FeatureBoxType.Detection,
                                onClick = {
                                    checkAndRequestCameraPermission(
                                        context = context,
                                        cameraPermissionLauncher = cameraPermissionLauncher,
                                        onAlreadyGranted = navigateToCamera
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.weight(1f))
//                        DetectBox1()
//                        DetectBox2()
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            FeatureBox(featureBoxType = FeatureBoxType.DropPoint, onClick = navigateToMaps)
                            Spacer(modifier = Modifier.weight(1f))
                            FeatureBox(featureBoxType = FeatureBoxType.Catalog, onClick = navigateToCatalog)
                        }

                        Spacer(modifier = Modifier.height(titlePaddingTop))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Articles",
                                style = MaterialTheme.typography.titleMedium,
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                text = "See all",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = yellow,
                                modifier = Modifier
                                    .clickable {
                                        navigateToArticle()
                                    }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(titlePaddingBottom))

                    if (latestArticleState != null) {

                        when(latestArticleState) {
                            is UiState.Loading -> {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                            is UiState.Error -> {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "No article to view")
                                }
                            }
                            is UiState.Success -> {
                                latestArticleState.data?.articleList?.size?.let {

                                    val pagerState = rememberPagerState(
                                        initialPage = 0,
                                        initialPageOffsetFraction = 0f,
                                    )

                                    HorizontalPager(
                                        pageCount = it,
                                        state = pagerState,
                                        contentPadding = PaddingValues(horizontal = 56.dp),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(180.dp)
                                    ) { page ->
                                        val articleList = latestArticleState.data.articleList

                                        articleList[page]?.let { it1 ->
                                            ArticleCardBig(
                                                modifier = Modifier
                                                    .width(280.dp)
                                                    .height(180.dp)
                                                    .graphicsLayer {
                                                        val pageOffset = (
                                                                (pagerState.currentPage - page) + pagerState
                                                                    .currentPageOffsetFraction
                                                                ).absoluteValue

                                                        lerp(
                                                            start = 0.85f,
                                                            stop = 1f,
                                                            fraction = 1f - pageOffset.coerceIn(
                                                                0f,
                                                                1f
                                                            )
                                                        ).also { scale ->
                                                            scaleX = scale
                                                            scaleY = scale
                                                        }
                                                        alpha = lerp(
                                                            start = 0.5f,
                                                            stop = 1f,
                                                            fraction = 1f - pageOffset.coerceIn(
                                                                0f,
                                                                1f
                                                            )
                                                        )
                                                    }
                                                    .clickable {
                                                        it1.id?.let { it2 ->
                                                            navigateToSingleArticle(
                                                                it2
                                                            )
                                                        }
                                                    },
                                                article = it1
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(titlePaddingTop))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Forums",
                                style = MaterialTheme.typography.titleMedium,
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                text = "See all",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = yellow
                            )
                        }

                        Spacer(modifier = Modifier.height(titlePaddingBottom))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            ArticleCardBig(
                                modifier = Modifier
                                    .width(320.dp)
                                    .height(186.dp),
                                article = ArticleList(
                                    componentId = 2,
                                    articleImageURL = null,
                                    name = "Forum title",
                                    id = 2,
                                    desc = "Forum description"
                                )
                            )
                        }
                    }
                }
            }

//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//                Spacer(modifier = Modifier.weight(1f))
//                DefaultBottomBar(
//                    selectedType = BottomBarItemType.Home,
//                    navigateToProfile = navigateToProfile,
//                    navigateToForum = navigateToForum,
//                    navigateToArticle = navigateToArticle,
//                    navigateToHome = navigateToHome
//                )
//            }
        }
    }
}

private fun checkAndRequestCameraPermission(context: Context, cameraPermissionLauncher: ManagedActivityResultLauncher<String, Boolean>, onAlreadyGranted: () -> Unit) {
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) -> {
            Log.d("Zhahrany", "Permission has already granted")
            onAlreadyGranted()
        }
        else -> {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}

@Preview
@Composable
fun HomeContentPreview() {
    TechwasMark02Theme {
        HomeContent(
            navigateToCamera = {},
            navigateToHome = {},
            navigateToArticle = {},
            navigateToForum = {},
            navigateToProfile = {},
            navigateToCatalog = {},
            navigateToMaps = {},
            navigateToSingleArticle = {},
            latestArticleState = UiState.Loading()
        )
    }
}