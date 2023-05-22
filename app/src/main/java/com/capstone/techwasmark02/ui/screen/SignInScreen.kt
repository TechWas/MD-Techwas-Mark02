package com.capstone.techwasmark02.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.techwasmark02.ui.component.DefaultButton
import com.capstone.techwasmark02.ui.component.DefaultTextField
import com.capstone.techwasmark02.ui.component.PasswordTextField
import com.capstone.techwasmark02.ui.component.SignInBanner
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun SignInScreen() {
    SignInContent()
}

@Composable
fun SignInContent() {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

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
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "Please sign in to continue",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(20.dp))

            DefaultTextField(
                value = email,
                onValueChange = { newValue -> email = newValue},
                labelText = "Email",
                placeHolderText = "user email",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                value = password,
                onValueChange = { newValue -> password = newValue},
                showPassword = showPassword,
                toggleShowPassword = { showPassword = !showPassword },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forgot your password?",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            DefaultButton(contentText = "Sign In", modifier = Modifier
                .fillMaxWidth()
                .height(50.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.bodySmall
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
        SignInContent()
    }
}