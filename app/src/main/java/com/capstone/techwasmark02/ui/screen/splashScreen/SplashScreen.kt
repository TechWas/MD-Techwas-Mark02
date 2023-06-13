package com.capstone.techwasmark02.ui.screen.splashScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.theme.Green77
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val userSession by viewModel.userSessionState.collectAsState()

    var animationState by remember {
        mutableStateOf(false)
    }

    val alphaAnim = animateFloatAsState(
        targetValue = if (animationState) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000 // 3 sec
        )
    )

    LaunchedEffect(key1 = true) {
        animationState = true
        delay(4000)
        // navController.navigate(Screen.Home.route)
    }

    LaunchedEffect(key1 = userSession) {
        delay(4000)

        if (userSession != null) {
            if (userSession!!.userLoginToken.accessToken == "") {
                navController.navigate(Screen.OnBoarding.route)
            } else {
                navController.navigate(Screen.Main.route)
            }
        }
    }

    SplashContent(alpha = alphaAnim.value)
}

@Composable
fun SplashContent(alpha: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(alpha)
            .background(color = Green77),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logo_onboarding_nooutline),
                contentDescription = null,
                modifier = Modifier
                    .width(57.27.dp)
                    .height(63.9.dp)
            )
            Text(
                text = "Techwaste",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 5.24.dp)
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = "Copyright © 2023",
            color= Color.White,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .padding(bottom = 32.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    TechwasMark02Theme {
        SplashContent(alpha = 1f)
    }
}