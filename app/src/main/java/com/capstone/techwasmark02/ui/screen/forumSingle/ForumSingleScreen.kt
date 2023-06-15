package com.capstone.techwasmark02.ui.screen.forumSingle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.data.model.UserSession
import com.capstone.techwasmark02.data.remote.response.Token
import com.capstone.techwasmark02.data.remote.response.UserId
import com.capstone.techwasmark02.ui.component.DefaultTopBar
import com.capstone.techwasmark02.ui.component.TransparentTopBar
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.screen.forum.ForumContent
import com.capstone.techwasmark02.ui.theme.Mist97
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ForumSingleScreen(
    viewModel: ForumSingleScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val userSession by viewModel.userSessionState.collectAsState()

    ForumSingleContent(
        userSession = userSession,
        navigateBackToForum = { navController.navigate("${Screen.Main.route}/1") }
    )
}

@Composable
fun ForumSingleContent(
    userSession: UserSession?,
    navigateBackToForum: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Mist97)
    ) {

        val scrollState = rememberScrollState()

        val coroutineScope = rememberCoroutineScope()

        var commentText by remember {
            mutableStateOf("")
        }

        var commentPosted by remember {
            mutableStateOf(false)
        }

        var showLastComment by remember {
            mutableStateOf(false)
        }

        var lastCommentText by remember {
            mutableStateOf("")
        }

        fun postLastComment() {
            coroutineScope.launch {
                commentPosted = true
                delay(2000)
                lastCommentText = commentText
                commentPosted = false
                commentText = ""
                showLastComment = true
            }
        }

        LaunchedEffect(key1 = showLastComment) {
            if (showLastComment) {
                scrollState.animateScrollTo(scrollState.maxValue)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
//                    .height(100.dp)
                    .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_forum_laptop_bekas),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Yogyakarta",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                    )
                    Text(
                        text = "Laptop",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Jual Laptop Mati Total",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Saya mempunyai laptop yang baru saja mati, pada saat tombol power tekan tidak ada respon apapun, kemungkinan masalah berada di battery. Selain itu kondisi masih bagus dan tidak ada kerusakan luar\nBagi yang berminat dapat mencoba menghubungi saya +6281396774583",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    )
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.tertiary)
                    .padding(top = 24.dp, bottom = 72.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Comment",
                        style = MaterialTheme.typography.labelLarge
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    UserInComment(
                        username = "Sesar Dito",
                        comment = "Ga minat beli, tapi pengen kenalan",
                        photoUrl = R.drawable.img_user_2
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Max)
                    ) {
                        Box(
                            modifier = Modifier
                                .width(60.dp)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(2.dp)
                                    .background(Color.LightGray)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            UserInComment(
                                username = "Ong Gabriel",
                                comment = "Sama aku saja",
                                photoUrl = R.drawable.img_user_3
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            UserInComment(
                                username = "Dwi Nugroho",
                                comment = "Tolong jaga sikap",
                                photoUrl = R.drawable.img_user_4
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Reply",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(start = 30.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    if (showLastComment && userSession != null) {
                        UserInComment(
                            username = userSession.userNameId.username,
                            comment = lastCommentText,
                            photoUrl = R.drawable.img_user_1
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Reply",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(start = 30.dp)
                        )

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.tertiary)
                    .padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (commentPosted) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f),
                                shape = RoundedCornerShape(20.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                } else {
                    CommentTextField(
                        value = commentText,
                        onValueChange = { newValue ->
                            commentText = newValue
                        },
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                IconButton(
                    onClick = { postLastComment() },
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_create),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.7f),
                            Color.Transparent
                        ),
                        startY = 0f,
                        endY = 600f
                    )
                )
        ) {
            TransparentTopBar(onClickNavigationIcon = { navigateBackToForum() }, pageTitle = "Detail Forum")
        }
    }
}

@Preview (showBackground = true)
@Composable
fun ForumSingleScreenPreview() {
    TechwasMark02Theme {
        ForumSingleContent(
            userSession = UserSession(
            userNameId = UserId(
                username = "Ghina",
                id = 1
            ),
            userLoginToken = Token(
                accessToken = ""
            )
        ),
            navigateBackToForum = {}
        )
    }
}

@Composable
fun UserInComment(
    username: String,
    comment: String,
    photoUrl: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = photoUrl),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(60.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = username,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )

            Text(
                text = comment,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f)
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun UserInCommentPreview() {
    TechwasMark02Theme {
        Box(
            modifier = Modifier
                .padding(10.dp)
        ) {
            UserInComment(username = "Ghori", comment = "Woow such a great insight", photoUrl = R.drawable.img_user_1)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var hasFocus by remember {
        mutableStateOf(false)
    }

    val focusColor = if (hasFocus) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f)

    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.onTertiary,
            containerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onTertiary,
            placeholderColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.7f)
        ),
        shape = MaterialTheme.shapes.large,
        placeholder = {
            Text(
                text = "Comments...",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        },
        modifier = modifier
            .border(
                width = if (hasFocus) 2.dp else 1.dp,
                color = focusColor,
                shape = MaterialTheme.shapes.large
            )
            .height(56.dp)
            .onFocusChanged { focusState -> hasFocus = focusState.hasFocus },
        textStyle = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Medium
        )
    )
}

@Preview (showBackground = true)
@Composable
fun CommentTextFieldPreview() {
    TechwasMark02Theme {
        var value by remember {
            mutableStateOf("")
        }

        Box(
            modifier = Modifier
                .padding(10.dp)
        ) {
            CommentTextField(
                value = value,
                onValueChange = { newValue -> value = newValue}
            )
        }
    }
}