package com.capstone.techwasmark02.ui.screen.forumSingle

import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.data.model.UserSession
import com.capstone.techwasmark02.data.remote.response.ForumCommentResponse
import com.capstone.techwasmark02.data.remote.response.ForumResponse
import com.capstone.techwasmark02.data.remote.response.PostForumCommentResponse
import com.capstone.techwasmark02.data.remote.response.Token
import com.capstone.techwasmark02.data.remote.response.UserId
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.TransparentTopBar
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.theme.Mist97
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun ForumSingleScreen(
    viewModel: ForumSingleScreenViewModel = hiltViewModel(),
    navController: NavHostController,
    forumId: Int
) {
    val userSession by viewModel.userSessionState.collectAsState()
    val forumState by viewModel.forumState.collectAsState()
    val forumCommentState by viewModel.forumCommentState.collectAsState()
    val postingCommentState by viewModel.postingCommentState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchForumById(id = forumId,)
        viewModel.fetchForumCommentByForumId(forumId)
    }

    BackHandler(true) {
        navController.navigate("${Screen.Main.route}/1")
    }

    ForumSingleContent(
        userSession = userSession,
        navigateBackToForum = { navController.navigate("${Screen.Main.route}/1") },
        forumState = forumState,
        forumCommentState = forumCommentState,
        fetchForumComment = { viewModel.fetchForumCommentByForumId(it)},
        forumId = forumId,
        postForumComment = { comment, currentForumId -> viewModel.postForumComment(comment, currentForumId)},
        postingCommentState = postingCommentState,
        clearPostingCommentState = { viewModel.clearPostingCommentState()}
    )
}

@Composable
fun ForumSingleContent(
    userSession: UserSession?,
    navigateBackToForum: () -> Unit,
    forumState: UiState<ForumResponse>?,
    forumCommentState: UiState<ForumCommentResponse>?,
    fetchForumComment: (Int) -> Unit,
    forumId: Int,
    postForumComment: (comment: String, currentForumId: Int) -> Unit,
    postingCommentState: UiState<PostForumCommentResponse>?,
    clearPostingCommentState: () -> Unit
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

        var firstRender by remember {
            mutableStateOf(true)
        }


        LaunchedEffect(key1 = forumCommentState) {
            if (!firstRender) {
                scrollState.animateScrollTo(scrollState.maxValue)
            }
        }

        if (forumState != null) {
            when(forumState) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(360.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is UiState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(360.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color.Red,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .padding(8.dp)

                        ) {
                            Text(
                                text = "Failed to fetch forum",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Red
                            )
                        }
                    }
                }
                is UiState.Success -> {
                    val currentForum = forumState.data?.forum?.get(0)

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
                            AsyncImage(
                                model = currentForum?.imageURL,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.LightGray)
                            )
//                            Image(
//                                painter = painterResource(id = R.drawable.img_forum_laptop_bekas),
//                                contentDescription = null,
//                                contentScale = ContentScale.Crop,
//                                modifier = Modifier
//                                    .fillMaxSize()
//                            )
                        }

                        Column(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(top = 24.dp, bottom = 8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                currentForum?.location?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                                    )
                                }
                                currentForum?.category?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            currentForum?.title?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            currentForum?.content?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }

                        }

                        if (forumCommentState != null) {
                            when(forumCommentState) {
                                is UiState.Loading -> {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                                is UiState.Error -> {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .border(
                                                    width = 1.dp,
                                                    color = Color.Red,
                                                    shape = RoundedCornerShape(20.dp)
                                                )
                                                .padding(8.dp)

                                        ) {
                                            Text(
                                                text = "Failed to fetch comment",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = Color.Red
                                            )
                                        }
                                    }
                                }
                                is UiState.Success -> {
                                    val commentList = forumCommentState.data?.article

                                    if (commentList != null) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(MaterialTheme.colorScheme.tertiary)
                                                .padding(top = 8.dp, bottom = 72.dp)
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 16.dp)
                                            ) {
                                                Text(
                                                    text = "Comment",
                                                    style = MaterialTheme.typography.labelLarge
                                                )

                                                Spacer(modifier = Modifier.height(10.dp))

                                                if (commentList != null) {
                                                    Column(
                                                        modifier = Modifier
                                                            .padding(bottom = 16.dp)
                                                            .widthIn(),
                                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                                    ) {
                                                        if (commentList.isNotEmpty()) {
                                                            commentList.forEach { comment ->
                                                                UserInComment(
                                                                    username = comment.username,
                                                                    comment = comment.comment,
                                                                    photoUrl = R.drawable.img_user_2
                                                                )

                                                                Text(
                                                                    text = "Reply",
                                                                    style = MaterialTheme.typography.bodySmall.copy(
                                                                        fontWeight = FontWeight.Bold
                                                                    ),
                                                                    color = MaterialTheme.colorScheme.primary,
                                                                    modifier = Modifier
                                                                        .padding(start = 30.dp)
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(1f)
                                                .background(MaterialTheme.colorScheme.tertiary)
                                        ) {
                                            Text(
                                                text = "Comment",
                                                style = MaterialTheme.typography.labelLarge,
                                                modifier = Modifier
                                                    .padding(top = 8.dp)
                                                    .padding(horizontal = 16.dp)
                                            )
                                        }
                                    }
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

                            if (postingCommentState != null) {
                                when(postingCommentState) {
                                    is UiState.Success -> {
                                        clearPostingCommentState()
                                        firstRender = false
                                        LaunchedEffect(Unit) {
                                            fetchForumComment(forumId)
                                        }
                                    }
                                    is UiState.Loading -> {
                                        Box(
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(56.dp)
                                                .border(
                                                    width = 2.dp,
                                                    color = MaterialTheme.colorScheme.onTertiary.copy(
                                                        alpha = 0.6f
                                                    ),
                                                    shape = RoundedCornerShape(20.dp)
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier
                                                    .size(24.dp)
                                            )
                                        }
                                    }
                                    is UiState.Error -> {
                                        val context = LocalContext.current
                                        Toast.makeText(context, "Fail to post comment", Toast.LENGTH_SHORT).show()
                                        clearPostingCommentState()
                                    }
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

//                            if (commentPosted) {
//                                Box(
//                                    modifier = Modifier
//                                        .weight(1f)
//                                        .height(56.dp)
//                                        .border(
//                                            width = 2.dp,
//                                            color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f),
//                                            shape = RoundedCornerShape(20.dp)
//                                        ),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    CircularProgressIndicator(
//                                        modifier = Modifier
//                                            .size(24.dp)
//                                    )
//                                }
//                            } else {
//                                CommentTextField(
//                                    value = commentText,
//                                    onValueChange = { newValue ->
//                                        commentText = newValue
//                                    },
//                                    modifier = Modifier
//                                        .weight(1f)
//                                )
//                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            IconButton(
                                onClick = {
                                    postForumComment( commentText, forumId)
                                    commentText = ""
                                },
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
            navigateBackToForum = {},
            forumState = null,
            forumCommentState = null,
            fetchForumComment = {},
            forumId = 0,
            postingCommentState = null,
            postForumComment = {comment: String, currentForumId: Int -> {}},
            clearPostingCommentState = {}
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