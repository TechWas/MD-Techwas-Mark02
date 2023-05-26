package com.capstone.techwasmark02.ui.screen.splashScreen

import android.window.SplashScreen
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.theme.Green77
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {

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

    SplashContent(alpha = alphaAnim.value)
}

@Composable
fun SplashContent(alpha: Float) {
    Box(
        modifier = Modifier
            .background(
                Green77
            )
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo_techwase),
            contentDescription = "logo techwaste",
            modifier = Modifier
                .size(100.dp)
                .alpha(alpha)
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