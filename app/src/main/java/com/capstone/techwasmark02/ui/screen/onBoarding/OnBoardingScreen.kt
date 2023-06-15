package com.capstone.techwasmark02.ui.screen.onBoarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.component.DefaultButton
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    navController: NavHostController
) {
    OnBoardingContent(
        navigateToHome = { navController.navigate(Screen.Home.route) },
        navigateToSignUp = { navController.navigate(Screen.SignUp.route) },
        navigateToSignIn = { navController.navigate(Screen.SignIn.route) },
        navigateToMain = { navController.navigate("${Screen.Main.route}/0") }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingContent(
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToMain: () -> Unit
) {
    val pageCount = 3
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()



    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HorizontalPager(
            count = pageCount,
            state = pagerState
        ) { page ->
            when(page) {
                0 -> {
                    OnBoardingContentFirst()
                }
                1 -> {
                    OnBoardingContentSecond()
                }
                2 -> {
                    OnBoardingContentThird(
                        navigateToHome = navigateToHome,
                        navigateToSignIn = navigateToSignIn,
                        navigateToSignUp = navigateToSignUp,
                        navigateToMain = navigateToMain
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AnimatedVisibility(
                visible = pagerState.currentPage != 2,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(top = 28.dp)
                        .padding(horizontal = 28.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(2)
                                }
                            },
                            modifier = Modifier
                                .height(28.dp)
                                .width(58.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray
                            )
                        ) {
                            Text(
                                text = "",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontFamily = FontFamily.Default,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }

                        Text(
                            text = "Skip",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            color = Color.Black.copy(alpha = 0.6f),
                            modifier = Modifier
                                .padding(top = 2.dp)
                                .clickable {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(2)
                                    }
                                }
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(bottom = 28.dp)
                    .padding(horizontal = 28.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .width(170.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val bulletColor =  Color.LightGray.copy(alpha = 0.8f)

                    Row(
                        modifier = Modifier
                            .weight(1f),
                        horizontalArrangement = Arrangement.Start
                    ) {

                        AnimatedVisibility(
                            visible = pagerState.currentPage != 0,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            IconButton(
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                    }
                                },
                                modifier = Modifier
                                    .background(Color.Transparent)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(CircleShape)
                                        .background(bulletColor),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_arrow_left),
                                        contentDescription = null,
                                        tint = Color.Black.copy(alpha = 0.4f)
                                    )
                                }
                            }
                        }
                    }

                    repeat(pageCount) { iteration ->
                        val color = if (pagerState.currentPage == iteration) Color(0xffFFDE59) else bulletColor

//                        val size = if (pagerState.currentPage == iteration) 14.dp else 10.dp

                        val sidDp:  Dp by animateDpAsState(
                        targetValue = if (pagerState.currentPage == iteration) 14.dp else 10.dp,
                        animationSpec = tween(durationMillis = 500)
                        )

                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(sidDp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .weight(1f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        AnimatedVisibility(
                            visible = pagerState.currentPage != 2,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            IconButton(
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                    }
                                },
                                modifier = Modifier
                                    .background(Color.Transparent)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(CircleShape)
                                        .background(bulletColor),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_arrow_right),
                                        contentDescription = null,
                                        tint = Color.Black.copy(alpha = 0.4f)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

            }
        }
    }
}

@Composable
fun OnBoardingContentFirst() {
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
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource( id = R.drawable.img_onboarding_ripple_peach_left, ),
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .width(240.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_onboarding_ripple_purple_right),
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .width(240.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = R.drawable.img_logo_onboarding),
                contentDescription = null,
                modifier = Modifier
                    .size(182.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Techwaste",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "It's an app developed by a\nteam of six students, aiming to\nenhance e-waste disposal!",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.7f
                )
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun OnBoardingContentSecond() {

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
                    painter = painterResource(id = R.drawable.img_onboarding_ripple_peach_right),
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .width(240.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_onboarding_ripple_purple_left),
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .width(240.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .height(IntrinsicSize.Max)
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val boxHeight = 98.dp

            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_feature_detect_box),
                    contentDescription = null,
                    modifier = Modifier
                        .width(92.dp)
                        .height(boxHeight)
                )
                Image(
                    painter = painterResource(id = R.drawable.img_feature_detect_illustration),
                    contentDescription = null,
                    modifier = Modifier
                        .width(134.dp)
                        .height(127.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.img_feature_detect_frame),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = (boxHeight + 40.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "E-waste Classification",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }

            Text(
                text = "Upload an image of your e-waste and\nTechwaste will determine where it belongs",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = 0.75f
                    )
                ),
                textAlign = TextAlign.Center
            )

//        Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .width(170.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .height(180.dp)
                            .padding(bottom = 8.dp),
                        contentAlignment = Alignment.BottomCenter,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_feature_article_box),
                            contentDescription = null,
                            modifier = Modifier
                                .height(98.dp)
                                .width(92.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.img_feature_article_illustration),
                            contentDescription = null,
                            modifier = Modifier
                                .size(145.dp)
                        )
                    }

                    Text(
                        text = "Articles",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                    )

                    Text(
                        text = "Know more about your\ne-waste and find out how to\nget fid of it properly",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(
                                alpha = 0.75f
                            )
                        ),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(170.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Box(
                        modifier = Modifier
                            .height(180.dp)
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        contentAlignment = Alignment.BottomCenter,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_feature_forum_box),
                            contentDescription = null,
                            modifier = Modifier
                                .height(98.dp)
                                .width(92.dp)
                        )

                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(48.dp))
                        Image(
                            painter = painterResource(id = R.drawable.img_feature_forum_illustration),
                            contentDescription = null,
                            modifier = Modifier
                                .height(166.dp)
                                .width(166.dp)
                        )
                    }


                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = "Forum",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                        )

                        Text(
                            text = "Ask questions, share your\nthoughts, drop any interesting\nfacts about e-waste",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.onBackground.copy(
                                    alpha = 0.75f
                                )
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OnBoardingContentThird(
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToMain: () -> Unit
) {
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
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_onboarding_ripple_peach_left),
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .width(240.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_onboarding_ripple_purple_right),
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .width(240.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp, bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_logo_onboarding_nooutline_green),
                    contentDescription = null,
                    modifier = Modifier
                        .height(164.dp)
                        .width(170.dp)
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Help us take care of\nelectronics waste.",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "What would you like to do?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = 0.7f
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DefaultButton(
                    contentText = "Let's get started",
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    buttonColors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffF7B595)
                    ),
                    onClick = navigateToSignUp
                )

                Spacer(modifier = Modifier.height(16.dp))

                DefaultButton(
                    contentText = "Already have account",
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    buttonColors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = Color.Black.copy(
                            alpha = 0.5f
                        )
                    ),
                    onClick = navigateToSignIn
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    TechwasMark02Theme {
        OnBoardingContent(
            navigateToSignIn = {},
            navigateToSignUp = {},
            navigateToHome = {},
            navigateToMain = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingContentFirstPreview() {
    TechwasMark02Theme {
        OnBoardingContentFirst()
    }
}

@Preview( showBackground = true)
@Composable
fun OnBoardingContentSecondPreview() {
    TechwasMark02Theme {
        OnBoardingContentSecond()
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingContentThirdPreview() {
    TechwasMark02Theme {
        OnBoardingContentThird(
            navigateToSignUp = {},
            navigateToHome = {},
            navigateToSignIn = {},
            navigateToMain = {}
        )
    }
}