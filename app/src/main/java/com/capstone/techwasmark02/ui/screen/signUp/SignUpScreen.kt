package com.capstone.techwasmark02.ui.screen.signUp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.capstone.techwasmark02.data.model.UserRegisterInfo
import com.capstone.techwasmark02.data.remote.response.UserRegisterResponse
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.component.DefaultButton
import com.capstone.techwasmark02.ui.component.DefaultTextField
import com.capstone.techwasmark02.ui.component.PasswordTextField
import com.capstone.techwasmark02.ui.component.SignUpBanner
import com.capstone.techwasmark02.ui.navigation.Screen
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun SignUpScreen(
    viewModel: SignUpScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val userToSignUpState by viewModel.userToSignUpState.collectAsState()
    val userToSignUpInfo by viewModel.userToSignUpInfo.collectAsState()

    SignUpContent(
        userToSignUpInfo = userToSignUpInfo,
        updateUserRegisterInfo = { viewModel.updateUserSignUpInfo(it) },
        userToSignUpState = userToSignUpState,
        signUpUser = { viewModel.signUpUser() },
        navigateToSignIn = { navController.navigate(Screen.SignIn.route)}
    )
}

@Composable
fun SignUpContent(
    userToSignUpInfo: UserRegisterInfo,
    updateUserRegisterInfo: (UserRegisterInfo) -> Unit,
    userToSignUpState: UiState<UserRegisterResponse>?,
    signUpUser: () -> Unit,
    navigateToSignIn: () -> Unit
) {

    var showPassword by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(top = 26.dp, bottom = 30.dp)
            .padding(horizontal = 20.dp)
    ) {
        SignUpBanner()

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
                text = "Sign Up",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "Just a few steps to help the earth managing e-waste!",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            DefaultTextField(
                value = userToSignUpInfo.fullname,
                onValueChange = { newValue ->
                    updateUserRegisterInfo(userToSignUpInfo.copy(
                        fullname = newValue
                    ))
                },
                labelText = "Full Name",
                placeHolderText = "user full name",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            DefaultTextField(
                value = userToSignUpInfo.email,
                onValueChange = { newValue ->
                    updateUserRegisterInfo(userToSignUpInfo.copy(
                        email = newValue
                    ))
                },
                labelText = "Email",
                placeHolderText = "user email",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                value = userToSignUpInfo.password,
                onValueChange = { newValue ->
                    updateUserRegisterInfo(userToSignUpInfo.copy(
                        password = newValue
                    ))
                },
                showPassword = showPassword,
                toggleShowPassword = { showPassword = !showPassword },
                modifier = Modifier.fillMaxWidth()
            )


            if(userToSignUpState != null) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    when(userToSignUpState) {
                        is UiState.Loading -> {
                            CircularProgressIndicator()
                        }
                        is UiState.Error -> {
                            userToSignUpState.message?.let {
                                Text(text = it)
                            }
                        }
                        is UiState.Success -> {

                            Toast.makeText(context, userToSignUpState.data?.message, Toast.LENGTH_SHORT).show()

                            LaunchedEffect(Unit) {
                                navigateToSignIn()
                            }
                        }
                    }
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            DefaultButton(
                contentText = "Sign Up",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = signUpUser
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingUpContentPreview() {
    TechwasMark02Theme {
        SignUpContent(
            userToSignUpState = null,
            userToSignUpInfo = UserRegisterInfo("", "", ""),
            updateUserRegisterInfo = {},
            signUpUser = {},
            navigateToSignIn = {}
        )
    }
}