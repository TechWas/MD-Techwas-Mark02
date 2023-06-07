package com.capstone.techwasmark02.ui.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import coil.compose.AsyncImage
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.data.remote.response.Component
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlin.random.Random

@Composable
fun CatalogCard(
    modifier: Modifier = Modifier,
    component: Component,
) {

    Box(
        modifier = modifier
            .width(300.dp)
            .height(100.dp)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(20.dp)
            )
//            .clip(RoundedCornerShape(20.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_bg_catalog_card),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp)
                .padding(start = 16.dp, end = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = component.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = component.desc,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .width(140.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.LightGray)
            ) {
                AsyncImage(
                    model = component.imageExample,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogCardPreview() {
    TechwasMark02Theme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            CatalogCard(
                component = Component(
                    desc = "A small portable personal computer that not very reliable and not very versatile.",
                    id = 1,
                    name = "Laptop",
                    imageExample = "https://picsum.photos/seed/${Random.nextInt()}/320/120"
                ),
            )
        }
    }
}