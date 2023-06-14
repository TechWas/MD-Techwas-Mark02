package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.techwasmark02.ui.componentType.ArticleFilterType
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun SelectableText(
    modifier: Modifier = Modifier,
    filterType: ArticleFilterType,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                onClick()
            }
            .background(
                if (selected) MaterialTheme.colorScheme.primary else Color.LightGray.copy(alpha = 0.6f)
            )
            .padding(vertical = 4.dp, horizontal = 8.dp),
    ) {
        Text(
            maxLines = 1,
            text = filterType.type,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier,
            color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SelectableTextPreview() {
    TechwasMark02Theme {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically){
            SelectableText(
                filterType = ArticleFilterType.WashingMachine,
                selected = true,
                onClick = {}
            )

            Spacer(modifier = Modifier.width(20.dp))

            SelectableText(
                filterType = ArticleFilterType.General,
                selected = false,
                onClick = {}
            )
        }
    }
}