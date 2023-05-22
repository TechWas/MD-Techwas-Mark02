package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    contentText: String
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp
        ),
    ) {
        Text(
            text = contentText,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun InverseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    contentText: String
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp
        )
    ) {
        Text(
            text = contentText,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun SmallButton(modifier: Modifier = Modifier, contentText: String, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(28.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onTertiary,
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        shape = MaterialTheme.shapes.large,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp
        ),
        contentPadding = PaddingValues(horizontal = 12.dp),
    ) {
        Text(
            text = contentText,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview (showBackground = true)
@Composable
fun ButtonPreview() {
    TechwasMark02Theme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            DefaultButton(modifier = Modifier.fillMaxWidth(), contentText = "Sign In")

            Spacer(modifier = Modifier.height(16.dp))

            InverseButton(modifier = Modifier.fillMaxWidth(), contentText = "Sign Out")

            Spacer(modifier = Modifier.height(16.dp))

            SmallButton(contentText = "LOCATE NOW")
        }
    }
}

