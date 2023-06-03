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
import coil.compose.rememberAsyncImagePainter
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.component.DefaultButton
import com.capstone.techwasmark02.ui.component.TransparentTopBar
import com.capstone.techwasmark02.ui.theme.Mist97
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import kotlin.random.Random

@Composable
fun SingleArticleScreen() {
    SingleArticleContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleArticleContent() {
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
                                    model = "https://picsum.photos/seed/${Random.nextInt()}/320/300",
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
                            Text(
                                text = "Battery",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "What you can do with used batteries.",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "Sumber: detik.com",
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
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                                "Vivamus nec feugiat quam, eu accumsan sapien. Integer " +
                                "auctor mauris sit amet fringilla commodo. Proin lorem " +
                                "lorem, sollicitudin sed volutpat a, pellentesque et odio. " +
                                "Suspendisse vitae libero et sem luctus hendrerit. Maecenas ut " +
                                "magna a odio laoreet tincidunt." + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                                "Vivamus nec feugiat quam, eu accumsan sapien. Integer " +
                                "auctor mauris sit amet fringilla commodo. Proin lorem " +
                                "lorem, sollicitudin sed volutpat a, pellentesque et odio. " +
                                "Suspendisse vitae libero et sem luctus hendrerit. Maecenas ut " +
                                "magna a odio laoreet tincidunt.",
                    )

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
        SingleArticleScreen()
    }
}