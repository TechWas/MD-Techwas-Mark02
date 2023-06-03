package com.capstone.techwasmark02.ui.screen.detectionResult

import android.net.Uri
import androidx.activity.compose.BackHandler
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.capstone.techwasmark02.ui.component.ArticleCardBig
import com.capstone.techwasmark02.ui.component.DefaultButton
import com.capstone.techwasmark02.ui.component.DefaultTopBar
import com.capstone.techwasmark02.ui.component.DetectionsResultBox
import com.capstone.techwasmark02.ui.component.DetectionsResultSaveBottomSheet
import com.capstone.techwasmark02.ui.component.InverseButton
import com.capstone.techwasmark02.ui.component.UsableComponentBottomSheet
import com.capstone.techwasmark02.ui.component.UsableComponentItem
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import kotlinx.coroutines.launch


@Composable
fun DetectionResultScreen(
    stringUri: String,
    detectionResult: String
) {
    DetectionResultContent(
        stringUri = stringUri,
        detectionResult = detectionResult
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DetectionResultContent(
    stringUri: String,
    detectionResult: String

) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded},
        skipHalfExpanded = true
    )

    var isOnBackClick by remember {
        mutableStateOf(false)
    }

    val photoUri = Uri.parse(stringUri)

//    BackHandler(modalSheetState.isVisible) {
//        coroutineScope.launch { modalSheetState.hide() }
//    }
//
//    BackHandler(!modalSheetState.isVisible) {
//        isOnBackClick = true
//        coroutineScope.launch { modalSheetState.show() }
//    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetContent = {
            if (isOnBackClick) {
                DetectionsResultSaveBottomSheet()
            } else {
                UsableComponentBottomSheet()
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

                        Text(text = detectionResult, color = Color.White)

                        DetectionsResultBox(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                        )

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
                                onClick = {
                                    isOnBackClick = false
                                    coroutineScope.launch {
                                        modalSheetState.show()
                                    }
                                }
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
}

@Preview
@Composable
fun DetectionResultContentPreview() {
    TechwasMark02Theme {
        DetectionResultContent(
            stringUri = "",
            detectionResult = ""
        )
    }
}

