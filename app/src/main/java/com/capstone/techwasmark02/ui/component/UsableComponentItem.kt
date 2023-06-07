package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.data.remote.response.SmallPart
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import java.io.File
import kotlin.random.Random

@Composable
fun UsableComponentItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    usableComponent: SmallPart
) {
    Row(
        modifier = modifier
            .width(152.dp)
            .height(60.dp)
            .shadow(
                elevation = 6.dp,
                shape = MaterialTheme.shapes.large
            )
            .background(MaterialTheme.colorScheme.tertiary)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = usableComponent.imageURL,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.place_holder),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(55.dp)
                .fillMaxHeight()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = usableComponent.name,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun UsableCompoenentItemPreview() {
    TechwasMark02Theme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            UsableComponentItem(
                onClick = {},
                usableComponent = SmallPart(
                    compID = 3,
                    description = "",
                    id = 2,
                    imageURL = "",
                    name = "RAM"
                )
            )
        }
    }
}