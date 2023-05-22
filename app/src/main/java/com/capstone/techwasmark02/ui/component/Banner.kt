package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun SignInBanner(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(150.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                shape = MaterialTheme.shapes.large,
                clip = true,
            )
            .clip(MaterialTheme.shapes.large)
            .background(
                MaterialTheme.colorScheme.primary,
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Welcome to TechWaste",
            style = MaterialTheme.typography.headlineSmall.copy(
                shadow = Shadow(
                    color = MaterialTheme.colorScheme.onTertiary.copy(
                        alpha = 0.8f
                    ),
                    offset = Offset(
                        x = 0f,
                        y = 14f
                    ),
                    blurRadius = 16f
                )
            ),
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun SignUpBanner(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(100.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                shape = MaterialTheme.shapes.large,
                clip = true,
            )
            .clip(MaterialTheme.shapes.large)
            .background(
                MaterialTheme.colorScheme.primary,
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Nice to meet you",
            style = MaterialTheme.typography.headlineSmall.copy(
                shadow = Shadow(
                    color = MaterialTheme.colorScheme.onTertiary.copy(
                        alpha = 0.8f
                    ),
                    offset = Offset(
                        x = 0f,
                        y = 14f
                    ),
                    blurRadius = 16f
                )
            ),
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun DropPointBanner(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(175.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                shape = MaterialTheme.shapes.large,
                clip = true,
            )
            .clip(MaterialTheme.shapes.large)
            .background(
                MaterialTheme.colorScheme.primary,
            )
            .padding(horizontal = 18.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Don't know where to",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Text(
                text = "put your e-waste?",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Text(
                text = "We've got you covered",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Spacer(modifier = Modifier.height(12.dp))
            SmallButton(contentText = "LOCATE NOW")
        }
    }
}

@Preview (showBackground = true)
@Composable
fun BannerPreview() {
    TechwasMark02Theme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            SignInBanner(modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            SignUpBanner()
        }
    }
}

@Preview (showBackground = true)
@Composable
fun HomeBannerPreview() {
    TechwasMark02Theme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            DropPointBanner()
        }
    }
    
}