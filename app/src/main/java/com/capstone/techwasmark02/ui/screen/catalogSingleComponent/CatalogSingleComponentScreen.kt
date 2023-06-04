package com.capstone.techwasmark02.ui.screen.catalogSingleComponent

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.capstone.techwasmark02.data.remote.response.Component
import com.capstone.techwasmark02.data.remote.response.ComponentResponse
import com.capstone.techwasmark02.data.remote.response.DetectionsResultResponse
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.ArticleCardBig
import com.capstone.techwasmark02.ui.component.DefaultButton
import com.capstone.techwasmark02.ui.component.DefaultTopBar
import com.capstone.techwasmark02.ui.component.InverseButton
import com.capstone.techwasmark02.ui.component.UsableComponentItem
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlin.random.Random

@Composable
fun CatalogSingleComponentScreen(
    viewModel: CatalogSingleComponentScreenViewModel = hiltViewModel(),
    componentJson: String
) {

    val componentState by viewModel.componentState.collectAsState()

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val adapter = moshi.adapter(Component::class.java)

    val component = adapter.fromJson(componentJson)

    if (component != null) {
        CatalogSingleComponentContent(component = component)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogSingleComponentContent(
    component: Component
) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                pageTitle = "Result",
                onClickNavigationIcon = {}
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
                        .height(440.dp)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 20.dp,
                                bottomEnd = 20.dp
                            )
                        )
                        .background(MaterialTheme.colorScheme.primary),
                ) {
                    AsyncImage(
                        model = component.imageExample,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alpha = 0.8f,
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(16.dp)
                    )
                    AsyncImage(
                        model = component.imageExample,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 402.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth()
                            .height(64.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.tertiary)
                            .padding(start = 20.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        component?.name?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }
                    }

//                    if (detectionsResultObj != null) {
//                        DetectionsResultBox(
//                            modifier = Modifier
//                                .padding(horizontal = 20.dp),
//                            predictionList = detectionsResultObj.predictions
//                        )
//                    }

                }
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp)
            ) {

                component?.desc?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Usable Components",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(
                        2
                    ),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.height(132.dp)
                ) {
                    items(3) { item ->
                        UsableComponentItem(
                            onClick = {}
//                            onClick = {
//                                isOnBackClick = false
//                                coroutineScope.launch {
//                                    modalSheetState.show()
//                                }
//                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Related Articles",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
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
            }

            Spacer(modifier = Modifier.height(28.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                DefaultButton(contentText = "Drop Point", modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(20.dp))

                InverseButton(contentText = "Check Out Forum", modifier = Modifier.fillMaxWidth())
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogSingleComponentScreenPreview() {
    TechwasMark02Theme {
        CatalogSingleComponentContent(
            component = Component(
                desc = "kdfiasu kadfiau kaud fiajdfkua dfja ufiauj dfkaud ifaju",
                id = 2,
                imageExample = "",
                name = "Laptop"
            )
        )
    }
}