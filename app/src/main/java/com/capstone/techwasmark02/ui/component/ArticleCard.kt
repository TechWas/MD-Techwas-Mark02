package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.data.remote.response.ArticleList
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun ArticleCardBig(
    modifier: Modifier = Modifier,
    article: ArticleList
) {
    ElevatedCard(
        modifier = modifier
            .height(175.dp),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.tertiary)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.LightGray)
            ) {
                AsyncImage(
                    model = article.articleImageURL,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(64.dp),
                verticalArrangement = Arrangement.Center
            ) {
                article.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                article.desc?.let {
                    HtmlText(
                        html = it,
                        textStyle = MaterialTheme.typography.bodySmall.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.Normal,
                            fontSize = 7.sp
                        ),
                        maxLine = 1
                    )
                }
            }
        }
    }
}

@Composable
fun ArticleCardSmall(
    modifier: Modifier = Modifier,
    imgUrl: String?,
    title: String?,
    description: String?,
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
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(107.dp),
                painter = rememberAsyncImagePainter(
//                    model = "https://picsum.photos/seed/${Random.nextInt()}/320/120",
                    model = imgUrl,
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
            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (description != null) {
                HtmlText(
                    html = description,
                    textStyle = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 7.sp
                    ),
                    maxLine = 1
                )
            }
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
            Column {
                ArticleCardBig(modifier = Modifier.width(240.dp), article = ArticleList(
                    componentId = 2,
                    articleImageURL = null,
                    name = "Do not throw electronic was bg",
                    id = 2,
                    desc = "Lorem ipsum and the sum of the sum si sum for the sum"
                )
                )
                Spacer(modifier = Modifier.height(20.dp))
                ArticleCardSmall(
                    modifier = Modifier.width(150.dp),
                    imgUrl = "",
                    title = "judul satu",
                    description = "deskripsi ajah",
                )
            }
        }
    }
}