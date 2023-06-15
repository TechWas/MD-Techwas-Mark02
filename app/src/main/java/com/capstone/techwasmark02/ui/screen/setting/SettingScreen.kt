package com.capstone.techwasmark02.ui.screen.setting

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.data.model.UserSession
import com.capstone.techwasmark02.data.remote.response.Token
import com.capstone.techwasmark02.data.remote.response.UserId
import com.capstone.techwasmark02.ui.component.InverseTopBar
import com.capstone.techwasmark02.ui.component.SettingItem
import com.capstone.techwasmark02.ui.componentType.SettingItemType
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import com.capstone.techwasmark02.ui.theme.red

@Composable
fun SettingScreen(
    navController: NavHostController,
    viewModel: SettingScreenViewModel = hiltViewModel()
) {

    val userSession by viewModel.userSessionState.collectAsState()

    SettingContent(
        navigateToProfile = { navController.popBackStack() },
        userSession = userSession,
        navigateToOnBoarding = { navController.navigate(Screen.OnBoarding.route)},
        logOutUser = { viewModel.clearUserSession() },
        navigateBackToMain = { navController.navigate("${Screen.Main.route}/3") }
    )
}

@Composable
fun SettingContent(
    navigateToProfile: () -> Unit,
    userSession: UserSession?,
    navigateToOnBoarding: () -> Unit,
    logOutUser: () -> Unit,
    navigateBackToMain: () -> Unit
) {
    BackHandler(true) {
        navigateBackToMain()
    }

    val settingItemList = listOf(
        SettingItemType.Password,
        SettingItemType.Comment,
        SettingItemType.Notification,
        SettingItemType.Language
    )

    LaunchedEffect(userSession) {
        if (userSession?.userNameId?.username == "-") {
            navigateToOnBoarding()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_profile_ripple_green_reverse),
                    contentDescription = null
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_profile_ripple_green),
                    contentDescription = null
                )
            }
        }


        Column(
            modifier = Modifier
                .padding(top = 100.dp)
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
                                    model = R.drawable.img_user_1,
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

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text =  userSession?.userNameId?.username ?: "User Full Name",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit",
                                tint = MaterialTheme.colorScheme.onTertiary,
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .size(24.dp)
                            )
                        }

//                       Text(
//                           text = "user@gmail.com",
//                           style = MaterialTheme.typography.bodyMedium,
//                           color = Color.White
//                       )

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
                                    onClick = logOutUser,
                                    modifier = Modifier
                                        .width(122.dp)
                                        .height(41.dp)
                                        .align(Alignment.End),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = red.copy(
                                            alpha = 0.9f
                                        )
                                    ),
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

        InverseTopBar(
            onClickNavigationIcon = {
                navigateBackToMain()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    TechwasMark02Theme {
        SettingContent(
            navigateToProfile = {},
            userSession = UserSession(
                userNameId = UserId(
                    username = "Ghina",
                    id = 1
                ),
                userLoginToken = Token(
                    accessToken = ""
                )
            ),
            navigateToOnBoarding = {},
            logOutUser = {},
            navigateBackToMain = {}
        )
    }
}
