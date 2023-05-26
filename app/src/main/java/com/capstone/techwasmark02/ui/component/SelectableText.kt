package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.techwasmark02.ui.theme.Black20
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun SelectableText(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = Color.Transparent
) {
    Box(
        modifier = modifier
            .background(
                color = if (selected) activeColor else inactiveColor,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = if (selected) Color.White else Black20
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SelectableTextPreview() {
    TechwasMark02Theme {
        Box(modifier = Modifier.padding(20.dp)){
            SelectableText(
                text = "Click me",
                selected = true
            )
        }
    }
}