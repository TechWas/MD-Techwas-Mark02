package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.componentType.FeatureBoxType
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import com.capstone.techwasmark02.ui.theme.gray

@Composable
fun FeatureBox(
    modifier: Modifier = Modifier,
    featureBoxType: FeatureBoxType,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(150.dp)
            .height(160.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = featureBoxType.backGround),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = featureBoxType.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(34.dp),
                    painter = painterResource(id = featureBoxType.icon),
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                SmallButton(
                    contentText = featureBoxType.buttonTitle,
                    onClick = onClick,
                    colorText = featureBoxType.buttonColor
                )
            }
        }
    }
}

@Composable
fun AboutUsBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(150.dp)
            .height(160.dp)
            .clip(
                RoundedCornerShape(
                    10.dp
                )
            )
            .background(gray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(
                        BorderStroke(
                            width = 2.dp,
                            color = Color.Black
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_question_mark),
                    contentDescription = null
                )
            }
            Text(
                text = "About Us",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetectBoxPreview() {
    TechwasMark02Theme {
        Box(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                FeatureBox(
                    featureBoxType = FeatureBoxType.Detection,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(20.dp))

                FeatureBox(featureBoxType = FeatureBoxType.Catalog, onClick = {})

                Spacer(modifier = Modifier.height(20.dp))

                FeatureBox(featureBoxType = FeatureBoxType.DropPoint, onClick = {})

                Spacer(modifier = Modifier.height(20.dp))

                AboutUsBox()
            }
        }
    }
}