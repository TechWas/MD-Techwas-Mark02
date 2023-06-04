package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.techwasmark02.ui.componentType.ArticleFilterType
import com.capstone.techwasmark02.ui.theme.Black20
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun SelectableText(
    modifier: Modifier = Modifier,
    filterType: ArticleFilterType,
    selected: Boolean
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       if(selected) {
           Text(
               text = filterType.type,
               color = Color.White,
               style = MaterialTheme.typography.bodySmall,
               modifier = Modifier
                   .clip(RoundedCornerShape(15.dp))
                   .background(MaterialTheme.colorScheme.primary)
                   .padding(horizontal = 11.dp, vertical = 6.dp)
           )
       } else {
           Text(
               text = filterType.type,
               color = MaterialTheme.colorScheme.onBackground,
               style = MaterialTheme.typography.bodySmall
           )
       }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectableTextPreview() {
    TechwasMark02Theme {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically){
            SelectableText(
                filterType = ArticleFilterType.General,
                selected = true
            )

            Spacer(modifier = Modifier.width(20.dp))

            SelectableText(
                filterType = ArticleFilterType.General,
                selected = false
            )
        }
    }
}