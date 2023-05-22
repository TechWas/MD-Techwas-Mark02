package com.capstone.techwasmark02.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.component.DefaultButton
import com.capstone.techwasmark02.ui.component.DefaultTopBar
import com.capstone.techwasmark02.ui.component.InverseButton
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun ImageDetectionScreen() {
    ImageDetectionContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetectionContent() {
    var imageUri by remember {
        mutableStateOf(null)
    }

    Scaffold(
        topBar = {
            DefaultTopBar(
                pageTitle = "Detect your e-waste",
                onClickNavigationIcon = {}
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 20.dp,
                            bottomStart = 20.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.primary)
                    .weight(3f),
                contentAlignment = Alignment.BottomEnd
            ) {
                if (imageUri != null) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(16.dp),
                        model = imageUri,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alpha = 0.7f
                    )
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize(),
                        model = imageUri,
                        contentDescription = "Selected image",
                        contentScale = ContentScale.Fit
                    )
                }
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(120.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 20.dp,
                                bottomEnd = 20.dp
                            )
                        )
                        .background(MaterialTheme.colorScheme.tertiary)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_rect_camera),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        IconButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_gallery),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                DefaultButton(contentText = "Detection", modifier = Modifier.width(280.dp))

                Spacer(modifier = Modifier.height(20.dp))

                InverseButton(contentText = "Choose Type", modifier = Modifier.width(280.dp))
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun ImageDetectionContentPreview() {
    TechwasMark02Theme {
        ImageDetectionContent()
    }
}