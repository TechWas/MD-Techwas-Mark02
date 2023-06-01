package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import com.capstone.techwasmark02.ui.theme.gray
import com.capstone.techwasmark02.ui.theme.purple
import com.capstone.techwasmark02.ui.theme.sakura

@Composable
fun DetectBox1(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = Modifier
            .width(150.dp)
            .height(160.dp)
            .border(
                BorderStroke(
                    width = 1.dp,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_bg_green),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ) {
                    Text(
                        text = "Detect\ne-waste",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(33.dp, 25.dp),
                        painter = painterResource(id = R.drawable.ic_center_focus),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SmallButton(
                        contentText = "Detect",
                        onClick = {},
                        colorText = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun DetectBox2(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = Modifier
            .width(150.dp)
            .height(160.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_bg_purple),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ) {
                    Text(
                        text = "E-waste\ncatalog",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(33.dp, 25.dp),
                        painter = painterResource(id = R.drawable.ic_menu_book),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SmallButton(
                        contentText = "Detect",
                        onClick = {},
                        colorText = purple
                    )
                }
            }
        }
    }
}

@Composable
fun DetectBox3(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = Modifier
            .width(150.dp)
            .height(160.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_bg_sakura),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ) {
                    Text(
                        text = "Nearby\ndrop point",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(33.dp, 25.dp),
                        painter = painterResource(id = R.drawable.ic_location_on),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SmallButton(
                        contentText = "Locate",
                        onClick = {},
                        colorText = sakura
                    )
                }
            }
        }
    }
}

@Composable
fun DetectBox4(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
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
                            width = 2.2.dp,
                            color = Color.Black
                        ),
                        shape = CircleShape
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_question_mark),
                        contentDescription = null
                    )
                }
            }
            Text(
                text = "About Us",
                style = MaterialTheme.typography.titleMedium
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
               DetectBox1()

               Spacer(modifier = Modifier.height(20.dp))

               DetectBox2()

               Spacer(modifier = Modifier.height(20.dp))

               DetectBox3()

               Spacer(modifier = Modifier.height(20.dp))

               DetectBox4()
           }
        }
    }
}