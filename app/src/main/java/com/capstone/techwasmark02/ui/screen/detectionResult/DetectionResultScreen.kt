package com.capstone.techwasmark02.ui.screen.detectionResult

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.data.remote.response.DetectionsResultResponse
import com.capstone.techwasmark02.data.remote.response.Prediction
import com.capstone.techwasmark02.data.remote.response.SmallPart
import com.capstone.techwasmark02.data.remote.response.UsableComponentsResponse
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.ArticleCardBig
import com.capstone.techwasmark02.ui.component.DefaultTopBar
import com.capstone.techwasmark02.ui.component.DetectionsResultBox
import com.capstone.techwasmark02.ui.component.UsableComponentBottomSheet
import com.capstone.techwasmark02.ui.component.UsableComponentItem
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch


@Composable
fun DetectionResultScreen(
    stringUri: String,
    detectionResult: String,
    viewModel: DetectionResultScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val selectedPrediction by viewModel.selectedPrediction.collectAsState()
    val usableComponentsListState by viewModel.usableComponentsListState.collectAsState()
    val currentlySelectedUsableComponentList by viewModel.currentlySelectedUsableComponentList.collectAsState()
    val relatedArticleListState by viewModel.relatedArticleListState.collectAsState()

    DetectionResultContent(
        stringUri = stringUri,
        detectionResult = detectionResult,
        selectedPrediction = selectedPrediction,
        updateSelectedPrediction = { viewModel.updateSelectedPrediction(it) },
        usableComponentsListState = usableComponentsListState,
        fetchAllUsableComponents = { viewModel.fetchAllUsableComponents(it) },
        currentlySelectedUsableComponentList = currentlySelectedUsableComponentList,
        updateCurrentlySelectedUsableComponentList = { viewModel.updateCurrentlySelectedUsableComponentList(it) },
        relatedArticleListState = relatedArticleListState,
        navigateToSingleArticle = { navController.navigate("${Screen.SingleArticle.route}/$it") },
        navigateToMain = { navController.navigate(Screen.Main.route) }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DetectionResultContent(
    stringUri: String,
    detectionResult: String,
    selectedPrediction: Int,
    updateSelectedPrediction: (Int) -> Unit,
    usableComponentsListState: List<UiState<UsableComponentsResponse>>?,
    fetchAllUsableComponents: (List<Prediction>) -> Unit,
    currentlySelectedUsableComponentList: List<SmallPart>,
    updateCurrentlySelectedUsableComponentList: (List<SmallPart>) -> Unit,
    relatedArticleListState: List<UiState<ArticleResultResponse>>?,
    navigateToSingleArticle: (idArticle: Int) -> Unit,
    navigateToMain: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded},
        skipHalfExpanded = false
    )

    val photoUri = Uri.parse(stringUri)

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val adapter = moshi.adapter(DetectionsResultResponse::class.java)
    val detectionsResultObj = adapter.fromJson(detectionResult)

    LaunchedEffect(Unit) {
        val componentIdList = detectionsResultObj?.predictions

        if (componentIdList != null) {
            fetchAllUsableComponents(componentIdList)
        }
    }

    var currentlyClickedUsableComponent by remember {
        mutableStateOf(0)
    }

    var isBottomSheetOpen by remember {
        mutableStateOf(false)
    }

    BackHandler(
        enabled = true
    ) {
        if (isBottomSheetOpen) {
            coroutineScope.launch {
                modalSheetState.hide()
                isBottomSheetOpen = false
            }
        } else {
            navigateToMain()
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetContent = {
            if (currentlySelectedUsableComponentList.isNotEmpty()) {
                val currentUsableComponents = currentlySelectedUsableComponentList[currentlyClickedUsableComponent]
                UsableComponentBottomSheet(
                    smallPart = currentUsableComponents
                )
            }
        }
    ) {

        Scaffold(
            topBar = {
                DefaultTopBar(
                    pageTitle = "Result",
                    onClickNavigationIcon = {
                        navigateToMain()
                    }
                )
            }
        ) { innerPadding ->
            val scrollState = rememberScrollState()

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
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
                                .height(500.dp)
                                .clip(
                                    RoundedCornerShape(
                                        bottomStart = 20.dp,
                                        bottomEnd = 20.dp
                                    )
                                )
                                .background(MaterialTheme.colorScheme.primary),
                        ) {
                            AsyncImage(
                                model = photoUri,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                alpha = 0.8f,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .blur(16.dp)
                            )
                            AsyncImage(
                                model = photoUri,
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
                            Text(
                                text = "Detected as",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )

                            if (detectionsResultObj != null) {
                                DetectionsResultBox(
                                    modifier = Modifier
                                        .padding(horizontal = 20.dp),
                                    predictionList = detectionsResultObj.predictions,
                                    updateSelectedPrediction = updateSelectedPrediction
                                )
                            }

                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = 28.dp)
                    ) {
                        Text(
                            text = "Usable Components",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        if (usableComponentsListState != null) {
                            val currentlySelectedPrediction = usableComponentsListState[selectedPrediction]
                            when(currentlySelectedPrediction) {
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
                                is UiState.Success -> {
                                    val usableComponentList = currentlySelectedPrediction.data?.smallParts

                                    usableComponentList?.size?.let {
                                        LazyVerticalGrid(
                                            columns = GridCells.Fixed(
                                                2
                                            ),
                                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                                            verticalArrangement = Arrangement.spacedBy(12.dp),
                                            modifier = Modifier.height((it * 72).dp)
                                        ) {
                                            items(usableComponentList.size) {index ->
                                                currentlyClickedUsableComponent = index
                                                updateCurrentlySelectedUsableComponentList(usableComponentList)

                                                UsableComponentItem(
                                                    onClick = {
                                                        isBottomSheetOpen = true
                                                        coroutineScope.launch {
                                                            modalSheetState.show()
                                                        }
                                                    },
                                                    usableComponent = usableComponentList[index]
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
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
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

                    if (relatedArticleListState != null) {
                        val currentSelectedArticleListState = relatedArticleListState[selectedPrediction]
                        when(currentSelectedArticleListState) {
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
                                val articleList = relatedArticleListState[selectedPrediction].data?.articleList

                                if (articleList?.isNotEmpty() == true) {
                                    LazyRow(
                                        contentPadding = PaddingValues(horizontal = 16.dp),
                                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        relatedArticleListState[selectedPrediction].data?.articleList?.let {
                                            items(
                                                count = it.size,
                                            ) { index ->
                                                articleList[index]?.let { it1 ->
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
                    } else {
                        Box(
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    Spacer(modifier = Modifier.height(80.dp))

                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    ThrowNSellButton()
                }
            }
        }

    }
}

@Composable
fun ThrowNSellButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 18.dp)
//                .border(
//                    width = 2.dp,
//                    color = MaterialTheme.colorScheme.primary,
//                    shape = RoundedCornerShape(20.dp)
//                )
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(20.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_trash_outline),
                    contentDescription = null,
                    modifier = Modifier
                        .size(26.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Throw it",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 18.dp)
//                .border(
//                    width = 2.dp,
//                    color = MaterialTheme.colorScheme.primary,
//                    shape = RoundedCornerShape(20.dp)
//                )
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(20.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sell),
                    contentDescription = null,
                    modifier = Modifier
                        .size(26.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Sell it",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
            }
        }

    }
}

//@Preview
//@Composable
//fun DetectionResultContentPreview() {
//    TechwasMark02Theme {
//        DetectionResultContent(
//            stringUri = "",
//            detectionResult = "",
//            selectedPrediction = 0,
//            updateSelectedPrediction = {},
//            usableComponentsListState = emptyList(),
//            fetchAllUsableComponents = {},
//            currentlySelectedUsableComponentList = emptyList(),
//            updateCurrentlySelectedUsableComponentList = {},
//            relatedArticleListState = emptyList(),
//            navigateToSingleArticle = {},
//            navigateToMain = {}
//        )
//    }
//}

@Preview(showBackground = true)
@Composable
fun ThrowNSellButtonPreview() {
    TechwasMark02Theme {
        Box(
            modifier = Modifier
                .padding(20.dp)
        ) {
            ThrowNSellButton()
        }
    }
}
