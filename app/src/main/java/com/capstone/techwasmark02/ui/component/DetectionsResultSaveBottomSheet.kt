package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun DetectionsResultSaveBottomSheet(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(172.dp)
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(horizontal = 20.dp)
            .padding(top = 24.dp, bottom = 42.dp)
    ) {
        Text(
            text = "Save your result",
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(36.dp))

        DefaultButton(
            contentText = "Save",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
}

@Preview
@Composable
fun DetectionsResultSaveBottomSheetPreview() {
    TechwasMark02Theme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(vertical = 20.dp)
        ) {
            DetectionsResultSaveBottomSheet()
        }
    }
}