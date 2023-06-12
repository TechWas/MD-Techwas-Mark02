package com.capstone.techwasmark02.ui.screen.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import com.capstone.techwasmark02.ui.component.ArticleCardSmall
import com.capstone.techwasmark02.ui.component.DefaultTopBar
import com.capstone.techwasmark02.ui.component.ForumBox
import com.capstone.techwasmark02.ui.component.SettingItem
import com.capstone.techwasmark02.ui.componentType.SettingItemType
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import com.capstone.techwasmark02.ui.theme.red
import kotlin.random.Random

@Composable
fun SettingScreen(
    navController: NavHostController
) {
    SettingContent(
        navigateToProfile = { navController.popBackStack() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingContent(
    navigateToProfile: () -> Unit
) {

    val settingItemList = listOf(
        SettingItemType.Password,
        SettingItemType.Comment,
        SettingItemType.Notification,
        SettingItemType.Language
    )

    Scaffold(
        topBar = {
            DefaultTopBar(
                pageTitle = "",
                onClickNavigationIcon = {
                    navigateToProfile()
                }
            )
        }
    ) { innerPadding ->

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Box(
                modifier = Modifier.fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.TopCenter),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box {
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
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clip(CircleShape)
                                    .background(Color.Black.copy(alpha = 0.3f))
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_camera_fill),
                                    contentDescription = "Camera",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .size(32.dp)
                                )
                            }
                        }

                       Spacer(modifier = Modifier.height(8.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "User Full Name",
                                style = MaterialTheme.typography.labelLarge,
                                color = Color.White
                            )
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit",
                                tint = Color.White,
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .size(24.dp)
                            )
                        }

                       Text(
                           text = "user@gmail.com",
                           style = MaterialTheme.typography.bodyMedium,
                           color = Color.White
                       )

                        Spacer(modifier = Modifier.height(40.dp))

                        Box(modifier = Modifier
                            .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                SettingItem(
                                    icon = settingItemList[0].icon,
                                    title = settingItemList[0].title,
                                )

                                SettingItem(
                                    icon = settingItemList[1].icon,
                                    title = settingItemList[1].title,
                                )

                                SettingItem(
                                    icon = settingItemList[2].icon,
                                    title = settingItemList[2].title,
                                )

                                SettingItem(
                                    icon = settingItemList[3].icon,
                                    title = settingItemList[3].title,
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Button(
                                    onClick = { },
                                    modifier = Modifier
                                        .width(122.dp)
                                        .height(41.dp)
                                        .align(Alignment.End),
                                    colors = ButtonDefaults.buttonColors(containerColor = red),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text(text = "Log out")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    TechwasMark02Theme {
        SettingContent(
            navigateToProfile = {}
        )
    }
}
