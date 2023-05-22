package com.capstone.techwasmark02.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.techwasmark02.ui.component.DefaultButton
import com.capstone.techwasmark02.ui.component.DefaultTextField
import com.capstone.techwasmark02.ui.component.InverseTopBar
import com.capstone.techwasmark02.ui.component.PasswordTextField
import com.capstone.techwasmark02.ui.component.SignUpBanner
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun SignUpScreen() {
    SignUpContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpContent() {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    var fullName by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            InverseTopBar(
                onClickNavigationIcon = {}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
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
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(20.dp))

                DefaultTextField(
                    value = fullName,
                    onValueChange = { newValue -> fullName = newValue},
                    labelText = "Full Name",
                    placeHolderText = "user full name",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

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


                Spacer(modifier = Modifier.weight(1f))

                DefaultButton(contentText = "Sign Up", modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp))

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingUpContentPreview() {
    TechwasMark02Theme {
        SignUpContent()
    }
}