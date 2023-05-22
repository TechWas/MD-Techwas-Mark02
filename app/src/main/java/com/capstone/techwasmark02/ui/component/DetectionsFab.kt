package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun DetectionsFab(modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = {},
        modifier = modifier
            .size(66.dp),
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_camera),
            contentDescription = null,
            modifier = Modifier
                .size(54.dp)
        )
    }
}

@Preview (showBackground = true)
@Composable
fun DetectionsFabPreview() {
    TechwasMark02Theme {
        Box(
            modifier = Modifier
                .padding(20.dp)
        ) {
            DetectionsFab()
        }
    }
}