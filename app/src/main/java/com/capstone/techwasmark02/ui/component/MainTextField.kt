package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelText: String,
    placeHolderText: String
) {
    var hasFocus by remember {
        mutableStateOf(false)
    }

    val focusColor = if (hasFocus) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f)

    Box(
        modifier = modifier
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeHolderText,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.textFieldColors(
                textColor = if (hasFocus) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onTertiary,
                containerColor = Color.Transparent,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                placeholderColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f),
                focusedTrailingIconColor = focusColor
            ),
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            modifier = modifier
                .onFocusChanged { focusState -> hasFocus = focusState.hasFocus }
                .border(
                    width = if (hasFocus) 3.dp else 1.5.dp,
                    color = if (hasFocus) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onTertiary.copy(
                        alpha = 0.6f
                    ),
                    shape = MaterialTheme.shapes.large
                ),
        )

        Text(
            text = labelText,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.sp
            ),
            modifier = Modifier
                .offset(
                    x = 16.dp,
                    y = -(10.dp)
                )
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 4.dp),
            color = focusColor
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    showPassword: Boolean,
    toggleShowPassword: () -> Unit,
    modifier: Modifier = Modifier
) {

    var hasFocus by remember {
        mutableStateOf(false)
    }

    val focusColor = if (hasFocus) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f)

    Box(
        modifier = Modifier
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .onFocusChanged { focusState -> hasFocus = focusState.hasFocus }
                .border(
                    width = if (hasFocus) 3.dp else 1.5.dp,
                    color = focusColor,
                    shape = MaterialTheme.shapes.large
                ),
            placeholder = {
                Text(
                    text = "user password",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.textFieldColors(
                textColor = focusColor,
                containerColor = Color.Transparent,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                placeholderColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f),
                focusedTrailingIconColor = focusColor,
                unfocusedLabelColor = focusColor
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(onClick =  toggleShowPassword ) {
                    if (showPassword) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_visibility_on),
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_visibility_off),
                            contentDescription = null
                        )
                    }
                }
            }
        )


        Text(
            text = "Password",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.sp
            ),
            modifier = Modifier
                .offset(
                    x = 16.dp,
                    y = -(10.dp)
                )
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 4.dp),
            color = focusColor
        )
    }
}

@Preview (showBackground = true)
@Composable
fun TextFieldPreview() {
    TechwasMark02Theme {

        var value by remember {
            mutableStateOf("")
        }

        var showPassword by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp)
        ) {
            DefaultTextField(
                value = value,
                onValueChange = { newValue ->
                    value = newValue
                },
                labelText = "Email",
                placeHolderText = "user email"
            )

            Spacer(modifier = Modifier.height(18.dp))

            PasswordTextField(
                value = value,
                onValueChange = { newValue ->
                    value = newValue
                },
                showPassword = showPassword,
                toggleShowPassword = {
                    showPassword = !showPassword
                }
            )
        }
    }
}