package com.capstone.techwasmark02.ui.component

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.theme.Black20
import com.capstone.techwasmark02.ui.theme.Green77
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import com.capstone.techwasmark02.ui.theme.Yellow77
import com.capstone.techwasmark02.ui.theme.poppins

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
        modifier = modifier.height(190.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Box(
            modifier = Modifier
                .height(175.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .background(
                    Green77.copy(alpha = 0.5f),
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(44.dp))
                    .background(
                        Green77.copy(alpha = 0.5f)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxSize()
                        .clip(RoundedCornerShape(65.dp))
                        .background(
                            Green77.copy(alpha = 0.7f)
                        )
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(150.dp))
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    val text = buildAnnotatedString {
                        append("Don't know where to put your ")
                        withStyle(style = SpanStyle(color = Yellow77)) {
                            append("e-waste?")
                        }
                    }

                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelLarge.copy(
                            shadow = Shadow(
                                color = Black20.copy(
                                    alpha = 0.25f,
                                ),
                                offset = Offset(0f, 4.0f),
                                blurRadius = 4f
                            ),
                            fontSize = 22.sp,
                            lineHeight = 25.sp,
                            letterSpacing = 0.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontFamily = poppins
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier.align(alignment =  Alignment.CenterHorizontally)) {
                        SmallButton(contentText = "LOCATE NOW")
                    }
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.trash_bucket),
            contentDescription = "Image",
            modifier = Modifier
                .height(400.dp)
                .width(200.dp)
                .offset(y = 0.dp, x = 10.dp)
        )
    }
}

@Composable
fun ForumBanner(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.height(190.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Box(
            modifier = Modifier
                .height(175.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .background(
                    Green77.copy(alpha = 0.5f),
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(39.dp))
                    .background(
                        Green77.copy(alpha = 0.5f)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxSize()
                        .clip(RoundedCornerShape(72.dp))
                        .background(
                            Green77.copy(alpha = 0.7f)
                        )
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(160.dp))
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(modifier = Modifier.align(alignment =  Alignment.CenterHorizontally)) {
                        SmallButton(contentText = "LOCATE NOW")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    val text = buildAnnotatedString {
                        append("Don't know where to put your ")
                        withStyle(style = SpanStyle(color = Yellow77)) {
                            append("e-waste?")
                        }
                    }

                    Text(
                        text = "Join the discussions!",
                        modifier = Modifier.width(200.dp),
                        style = MaterialTheme.typography.labelSmall.copy(
                            shadow = Shadow(
                                color = Black20.copy(
                                    alpha = 0.25f,
                                ),
                                offset = Offset(0f, 4.0f),
                                blurRadius = 4f,
                            ),
                            fontSize = 13.sp,
                            lineHeight = 25.sp,
                            letterSpacing = 0.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontFamily = poppins,
                            textAlign = TextAlign.End
                        )
                    )

                    Text(
                        text = "Get involved in discussion with other users.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 11.sp,
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontFamily = poppins
                    )

                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.forum_chat),
            contentDescription = "Image",
            modifier = Modifier
                .height(400.dp)
                .width(170.dp)
                .offset(y = 0.dp, x = 20.dp)
        )
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

            Spacer(modifier = Modifier.height(16.dp))

            ForumBanner()
        }
    }
    
}