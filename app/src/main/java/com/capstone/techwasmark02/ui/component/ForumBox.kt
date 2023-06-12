package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import kotlin.random.Random

@Composable
fun ForumBox(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .width(328.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .background(Color.White)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Row(
            modifier
                .fillMaxSize()
                .padding(end = 27.5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(
                        topStart= 20.dp,
                        bottomStart= 20.dp
                    )),
                painter = rememberAsyncImagePainter(
                        model = "https://picsum.photos/seed/${Random.nextInt()}/320/120",
                        placeholder = painterResource(id = R.drawable.place_holder),
                    ),
                contentScale = ContentScale.Crop,
                contentDescription = null
                )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(start = 8.2.dp)
            ) {
                Text(
                    text = "Title",
                    style = MaterialTheme.typography.labelMedium,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Subtitle",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    overflow = TextOverflow.Ellipsis
                )
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForumBoxPreview() {
    TechwasMark02Theme() {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)) {
            ForumBox()
        }
    }
}