package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetectionsResultBox(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .size(84.dp)
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )
        }

        VerticalPager(
            pageCount = dummyDetectionResultList.size,
            modifier = Modifier
                .height(84.dp),
            pageSpacing = (-50).dp,
            state = pagerState,
        ) { page ->
            ResultListItem(
                detectionResult = dummyDetectionResultList[page],
                pagerState = pagerState,
                page = page,
                modifier = Modifier
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            val prevButtonVisible by remember {
                derivedStateOf {
                    pagerState.currentPage > 0
                }
            }

            val nextButtonVisible by remember {
                derivedStateOf {
                    pagerState.currentPage < dummyDetectionResultList.size - 1
                }
            }

            IconButton(onClick = {
                val prevPageIndex = pagerState.currentPage - 1
                coroutineScope.launch { pagerState.animateScrollToPage(prevPageIndex) }
            },
                enabled = prevButtonVisible,
                ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_up),
                    contentDescription = null,
                    tint =  MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier
                        .alpha(if (prevButtonVisible) 1f else 0.3f),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                val nextPageIndex = pagerState.currentPage + 1
                coroutineScope.launch { pagerState.animateScrollToPage(nextPageIndex) }
            },
                enabled = nextButtonVisible
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier
                        .alpha(if (nextButtonVisible) 1f else 0.3f)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResultListItem(
    detectionResult: DummyDetectionResult,
    pagerState: PagerState,
    page: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = detectionResult.componentType,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                    alpha = lerp(
                        start = 0.2f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    scaleX = lerp(
                        start = 0.6f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    scaleY = lerp(
                        start = 0.6f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    transformOrigin = TransformOrigin(
                        pivotFractionY = 0.4f,
                        pivotFractionX = 0.1f
                    )
                }
        )
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(84.dp)
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${detectionResult.detectionPercentage}%",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 28.sp
                ),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleX = lerp(
                            start = 0.7f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleY = lerp(
                            start = 0.7f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        transformOrigin = TransformOrigin(
                            pivotFractionY = 0.4f,
                            pivotFractionX = 0.5f
                        )
                    }
            )
        }
    }
}


@Preview (showBackground = false)
@Composable
fun DetectionsResultBoxPreview() {
    TechwasMark02Theme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            DetectionsResultBox()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview (showBackground = true)
@Composable
fun ResultListItemPreview() {
    TechwasMark02Theme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(20.dp)
        ) {
            ResultListItem(
                detectionResult = DummyDetectionResult(
                    componentType = "Laptop",
                    detectionPercentage = 70
                ),
                pagerState = PagerState(
                    initialPage = 0,
                    initialPageOffsetFraction = 0f,
                ),
                page = 0
            )
        }
    }
}

data class DummyDetectionResult(
    val componentType: String,
    val detectionPercentage: Int
)

private val dummyDetectionResultList: List<DummyDetectionResult> = listOf(
    DummyDetectionResult(
        componentType = "Laptop",
        detectionPercentage = 70
    ),
    DummyDetectionResult(
        componentType = "Mesin Cuci",
        detectionPercentage = 20
    ),
    DummyDetectionResult(
        componentType = "Rice Cooker",
        detectionPercentage = 10
    )
)
