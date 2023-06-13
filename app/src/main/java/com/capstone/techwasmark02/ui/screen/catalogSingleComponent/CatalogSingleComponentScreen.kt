package com.capstone.techwasmark02.ui.screen.catalogSingleComponent

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.data.remote.response.Component
import com.capstone.techwasmark02.data.remote.response.SmallPart
import com.capstone.techwasmark02.data.remote.response.UsableComponentsResponse
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.ArticleCardBig
import com.capstone.techwasmark02.ui.component.DefaultButton
import com.capstone.techwasmark02.ui.component.DefaultTopBar
import com.capstone.techwasmark02.ui.component.InverseButton
import com.capstone.techwasmark02.ui.component.UsableComponentBottomSheet
import com.capstone.techwasmark02.ui.component.UsableComponentItem
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch

@Composable
fun CatalogSingleComponentScreen(
    viewModel: CatalogSingleComponentScreenViewModel = hiltViewModel(),
    componentJson: String,
    navController: NavHostController
) {

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val adapter = moshi.adapter(Component::class.java)
    val component = adapter.fromJson(componentJson)

    val usableComponentsState by viewModel.usableComponentsState.collectAsState()
    val usableComponentList by viewModel.usableComponentList.collectAsState()
    val relatedArticleListState by viewModel.relatedArticleListState.collectAsState()

    if (component != null) {
        CatalogSingleComponentContent(
            component = component,
            usableComponentsState = usableComponentsState,
            updateUsableComponentsState = { viewModel.updateUsableComponentsState(it) },
            usableComponentList = usableComponentList,
            updateUsableComponentsList = { viewModel.updateUsableComponentList(it) },
            relatedArticleListState = relatedArticleListState,
            updateRelatedArticleListState = { viewModel.updateRelatedArticleListState(it) },
            navigateToSingleArticle = { navController.navigate("${Screen.SingleArticle.route}/$it") }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CatalogSingleComponentContent(
    component: Component,
    usableComponentsState: UiState<UsableComponentsResponse>?,
    updateUsableComponentsState: (Int) -> Unit,
    usableComponentList: List<SmallPart>,
    updateUsableComponentsList: (List<SmallPart>) -> Unit,
    relatedArticleListState: UiState<ArticleResultResponse>?,
    updateRelatedArticleListState: (Int) -> Unit,
    navigateToSingleArticle: (idArticle: Int) -> Unit,

    ) {

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded},
        skipHalfExpanded = false
    )

    val coroutineScope = rememberCoroutineScope()

    var currentlyClickedUsableComponent by remember {
        mutableStateOf(0)
    }

    var isBottomSheetOpen by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        updateUsableComponentsState(component.id)
        updateRelatedArticleListState(component.id)
    }

    BackHandler(isBottomSheetOpen) {
        if (isBottomSheetOpen) {
            coroutineScope.launch {
                modalSheetState.hide()
                isBottomSheetOpen = false
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetContent = {
            if (usableComponentList.isNotEmpty()) {
                UsableComponentBottomSheet(
                    smallPart = usableComponentList[currentlyClickedUsableComponent]
                )
            }
        }
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

                var componentWidth by remember {
                    mutableStateOf(0.dp)
                }
                val density = LocalDensity.current


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
                            .background(Color(0xff656565))
                            .onGloballyPositioned {
                                componentWidth = with(density) {
                                    it.size.width.toDp()
                                }
                            }
                            .offset(
                                x = -(componentWidth * 15 / 88)
                            ),
                    ) {
//                        AsyncImage(
//                            model = component.imageExample,
//                            contentDescription = null,
//                            contentScale = ContentScale.Crop,
//                            alpha = 0.8f,
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .blur(16.dp)
//                        )
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
                                .shadow(
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .background(MaterialTheme.colorScheme.tertiary)
                                .padding(start = 20.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = component.name,
                                style = MaterialTheme.typography.headlineSmall
                            )
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

                    Text(
                        text = component.desc,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Usable Components",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    if (usableComponentsState != null) {
                        when(usableComponentsState) {
                            is UiState.Success -> {
                                usableComponentsState.data?.smallParts?.size?.let {
                                    LazyVerticalGrid(
                                        columns = GridCells.Fixed(
                                            2
                                        ),
                                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                                        verticalArrangement = Arrangement.spacedBy(12.dp),
                                        modifier = Modifier
                                            .height((it * 72).dp),
                                    ) {
                                        items(usableComponentsState.data.smallParts.size) { index ->
                                            currentlyClickedUsableComponent = index
                                            updateUsableComponentsList(usableComponentsState.data.smallParts)

                                            UsableComponentItem(
                                                onClick =  {
                                                    isBottomSheetOpen = true
                                                    coroutineScope.launch {
                                                        modalSheetState.show()
                                                    }
                                                },
                                                usableComponent = usableComponentsState.data.smallParts[index]
                                            )
                                        }

                                    }
                                }
                            }
                            is UiState.Error -> {
                                Box(
                                    modifier = Modifier
                                        .height(100.dp)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Fail to fetch usable component",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.Red
                                    )
                                }
                            }
                            is UiState.Loading -> {
                                Box(
                                    modifier = Modifier
                                        .height(100.dp)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Related Articles",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }


                if (relatedArticleListState != null) {
                    when(relatedArticleListState) {
                        is UiState.Loading -> {
                            Box(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        is UiState.Error -> {
                            Box(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Fail to fetch article",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.Red
                                )
                            }
                        }
                        is UiState.Success -> {
                            val articleList = relatedArticleListState.data?.articleList

                            if (articleList?.isNotEmpty() == true) {
                                LazyRow(
                                    contentPadding = PaddingValues(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    relatedArticleListState.data.articleList.let {
                                        if (it.isNotEmpty()) {
                                            items(
                                                count = it.size,
                                            ) { index ->
                                                articleList.get(index)?.let { it1 ->
                                                    ArticleCardBig(
                                                        modifier = Modifier
                                                            .width(150.dp)
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
            ),
            usableComponentsState = UiState.Loading(),
            updateUsableComponentsState = {},
            usableComponentList = emptyList(),
            updateUsableComponentsList = {},
            relatedArticleListState = UiState.Loading(),
            updateRelatedArticleListState = {},
            navigateToSingleArticle = {}
        )
    }
}