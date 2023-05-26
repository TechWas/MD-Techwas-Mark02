package com.capstone.techwasmark02.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import com.capstone.techwasmark02.ui.component.ArticleCardBig
import com.capstone.techwasmark02.ui.component.DefaultBottomBar
import com.capstone.techwasmark02.ui.component.DetectionsFab
import com.capstone.techwasmark02.ui.component.DropPointBanner
import com.capstone.techwasmark02.ui.component.ForumBanner
import com.capstone.techwasmark02.ui.component.SelectableText
import com.capstone.techwasmark02.ui.component.UserGreet
import com.capstone.techwasmark02.ui.componentType.BottomBarItemType
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import kotlin.math.absoluteValue

@Composable
fun HomeScreen() {
    HomeContent()
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeContent() {

    val filterTypeList = listOf(
        "General",
        "Laptop",
        "Baterai",
        "Kabel",
        "General",
        "Laptop",
        "Baterai",
        "Kabel",
        "General",
        "Laptop",
        "Baterai",
        "Kabel",
        "General",
        "Laptop",
        "Baterai",
        "Kabel",
    )

    Scaffold(

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 60.dp, top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    UserGreet(userName = "Zhahrany")

                    Spacer(modifier = Modifier.height(10.dp))

                    DropPointBanner()

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "What's New?",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        items(
                            items = filterTypeList,
                        ) { item ->
                            SelectableText(
                                text = item,
                                selected = false
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                val pagerState = rememberPagerState()
                HorizontalPager(
                    pageCount = 10,
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 70.dp),
                    modifier = Modifier.fillMaxWidth()
                ) { page ->
                    ArticleCardBig(
                            modifier = Modifier
                                .width(240.dp)
                                .graphicsLayer {
                                    // Calculate the absolute offset for the current page from the
                                    // scroll position. We use the absolute value which allows us to mirror
                                    // any effects for both directions
                                    val pageOffset = (
                                            (pagerState.currentPage - page) + pagerState
                                                .currentPageOffsetFraction
                                            ).absoluteValue

                                    lerp(
                                        start = 0.85f,
                                        stop = 1f,
                                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                    ).also { scale ->
                                        scaleX = scale
                                        scaleY = scale
                                    }
                                    alpha = lerp(
                                        start = 0.5f,
                                        stop = 1f,
                                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                    )
                                }
                        )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {

                    ForumBanner()

                }
            }

            DetectionsFab(
                modifier = Modifier.padding(bottom = 25.dp)
            )

            DefaultBottomBar(selectedType = BottomBarItemType.Home)
        }
    }
}

@Preview
@Composable
fun HomeContentPreview() {
    TechwasMark02Theme {
        HomeContent()
    }
}