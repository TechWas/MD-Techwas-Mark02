package com.capstone.techwasmark02.ui.screen.signIn

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.capstone.techwasmark02.data.model.UserLoginInfo
import com.capstone.techwasmark02.data.model.UserSession
import com.capstone.techwasmark02.data.remote.response.UserLoginResponse
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.DefaultButton
import com.capstone.techwasmark02.ui.component.DefaultTextField
import com.capstone.techwasmark02.ui.component.PasswordTextField
import com.capstone.techwasmark02.ui.component.SignInBanner
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun SignInScreen(
    viewModel: SignInScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val userToSignInState by viewModel.userToSignInState.collectAsState()
    val userToSignInInfo by viewModel.userToSignInInfo.collectAsState()
//    val userSessionState by viewModel.userSessionState.collectAsState()

    SignInContent(
        userToSignInInfo = userToSignInInfo,
        updateUserLoginInfo = { viewModel.updateUserSignInInfo(it) },
        userToSignInState = userToSignInState,
        signInUser = { viewModel.signInUser() },
        saveUserSession = { viewModel.saveUserSession() },
        navigateToMain = { navController.navigate(Screen.Main.route) }
//        userSessionState = userSessionState
    )
}

@Composable
fun SignInContent(
    userToSignInInfo: UserLoginInfo,
    updateUserLoginInfo: (UserLoginInfo) -> Unit,
    userToSignInState: UiState<UserLoginResponse>?,
    signInUser: () -> Unit,
    saveUserSession: () -> Unit,
    navigateToMain: () -> Unit
//    userSessionState: UserSession?
) {

    var showPassword by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        SignInBanner(
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .shadow(
                    elevation = 6.dp,
                    shape = MaterialTheme.shapes.large,
                    clip = true
                )
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(horizontal = 20.dp, vertical = 30.dp)
        ) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.8f)
            )

            Text(
                text = "Please sign in! We're excited to have you on board!",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(28.dp))

            DefaultTextField(
                value = userToSignInInfo.email,
                labelText = "Email",
                placeHolderText = "user email",
                modifier = Modifier.fillMaxWidth() ,
                onValueChange = { newValue ->
                    updateUserLoginInfo(userToSignInInfo.copy(
                        email = newValue
                    ))
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                value = userToSignInInfo.password,
                showPassword = showPassword,
                toggleShowPassword = { showPassword = !showPassword },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { newValue ->
                    updateUserLoginInfo(userToSignInInfo.copy(
                        password = newValue
                    ))
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forgot your password?",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.8f)
                )
            }

            if(userToSignInState != null) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    when(userToSignInState) {
                        is UiState.Loading -> {
                            CircularProgressIndicator()
                        }
                        is UiState.Error -> {
                            userToSignInState.message?.let {
                                Text(text = it)
                            }
                        }
                        is UiState.Success -> {
                            saveUserSession()

                            val username = userToSignInState.data?.loginResult?.userId?.username
                            Toast.makeText(context, "Welcome $username", Toast.LENGTH_SHORT).show()

                            LaunchedEffect(Unit) {
                                navigateToMain()
                            }
                        }
                    }
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            DefaultButton(
                contentText = "Sign In",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = signInUser
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = "sign up.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.primary
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun SingInContentPreview() {
    TechwasMark02Theme {
        SignInContent(
            userToSignInState = null,
            userToSignInInfo = UserLoginInfo("", ""),
            updateUserLoginInfo = {},
            signInUser = {},
            saveUserSession = {},
            navigateToMain = {}
//            userSessionState = null
        )
    }
}