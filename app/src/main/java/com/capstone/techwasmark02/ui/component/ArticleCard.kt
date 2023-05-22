package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import kotlin.random.Random

@Composable
fun ArticleCardBig(
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Box(
            modifier = Modifier
                .height(107.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(107.dp),
                painter = rememberAsyncImagePainter(
                    model = "https://picsum.photos/seed/${Random.nextInt()}/320/120",
                    placeholder = painterResource(id = R.drawable.place_holder),
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Do not throw electronic waste carelessly",
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1
            )
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur...",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleCardPreview() {
    TechwasMark02Theme() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp)
        ) {
            ArticleCardBig(modifier = Modifier.width(240.dp))
        }
    }
}