package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.componentType.BottomBarItemType
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import com.capstone.techwasmark02.ui.theme.customShape.BottomNavShape
import com.capstone.techwasmark02.ui.theme.customShape.BottomNavShape2

@Composable
fun DefaultBottomBar(
    modifier: Modifier = Modifier,
    selectedType: BottomBarItemType
) {
    NavigationBar(
        modifier = modifier
            .graphicsLayer {
                shape = BottomNavShape()
                clip = true
            }
            .fillMaxWidth()
            .height(60.dp),
        containerColor = MaterialTheme.colorScheme.inverseSurface,
        contentColor = MaterialTheme.colorScheme.inverseOnSurface,

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))

            BottomBarItem(bottomBarItemType = BottomBarItemType.Home, selectedType = selectedType)

            Spacer(modifier = Modifier.weight(1f))

            BottomBarItem(bottomBarItemType = BottomBarItemType.Forum, selectedType = selectedType)

            Spacer(modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.width(80.dp))

            Spacer(modifier = Modifier.weight(1f))

            BottomBarItem(bottomBarItemType = BottomBarItemType.Article, selectedType = selectedType)

            Spacer(modifier = Modifier.weight(1f))

            BottomBarItem(bottomBarItemType = BottomBarItemType.Profile, selectedType = selectedType)

            Spacer(modifier = Modifier.weight(1f))

        }
    }
}

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    bottomBarItemType: BottomBarItemType,
    selectedType: BottomBarItemType
) {
    Column(
        modifier = modifier
            .width(44.dp)
            .height(44.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = bottomBarItemType.icon),
            contentDescription = null,
            tint = if (selectedType == bottomBarItemType) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.inverseOnSurface
                }

        )
        Text(
            text = bottomBarItemType.title,
            style = MaterialTheme.typography.labelSmall,
            color = if (selectedType == bottomBarItemType) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.inverseOnSurface
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultBottomBarPreview() {
    TechwasMark02Theme() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            DefaultBottomBar(selectedType = BottomBarItemType.Home)
        }
    }
}

@Composable
fun CheckBottomNav(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                shape = BottomNavShape()
                clip = true
            }
            .fillMaxWidth()
            .height(58.dp)
            .background(MaterialTheme.colorScheme.inverseSurface)
    ) {

    }
}

@Preview (showBackground = true)
@Composable
fun CheckBottomNavPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        CheckBottomNav()
    }
}