package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.capstone.techwasmark02.data.remote.response.SmallPart
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun UsableComponentBottomSheet(
    modifier: Modifier = Modifier,
    smallPart: SmallPart
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(360.dp)
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(top = 32.dp, bottom = 42.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = smallPart.name,
                style = MaterialTheme.typography.labelLarge
            )

            Text(
                text = smallPart.description,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.large)
                    .background(Color.LightGray)
            ) {
                AsyncImage(
                    model = smallPart.imageURL,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }

}

@Preview (showBackground = true)
@Composable
fun UsableComponentBottomSheetPreview() {
    TechwasMark02Theme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(vertical = 20.dp)
        ) {
            UsableComponentBottomSheet(
                smallPart = SmallPart(
                    compID = 0,
                    description = "jkdfau adkjfaku aksdufka ufausd f",
                    id = 0,
                    imageURL = "",
                    name = "RAM"
                )
            )
        }
    }
}
