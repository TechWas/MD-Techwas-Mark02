package com.capstone.techwasmark02.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.techwasmark02.ui.componentType.BottomBarItemType
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme

@Composable
fun DefaultBottomBar(
    modifier: Modifier = Modifier,
    selectedType: BottomBarItemType,
    onClickBottomNavType: (bottomBarType: BottomBarItemType) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(bottom = 8.dp)
    ) {
        NavigationBar(
            modifier = modifier
                .shadow(
                    elevation = 8.dp,
//                    shape = BottomNavShape(),
                    clip = true
                )
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(64.dp),
            containerColor = MaterialTheme.colorScheme.inverseSurface,
            contentColor = MaterialTheme.colorScheme.inverseOnSurface,
            tonalElevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BottomBarItem(
                    bottomBarItemType = BottomBarItemType.Home,
                    selectedType = selectedType,
                    onClick = {
                        onClickBottomNavType(it)
                    },
                )

                Spacer(modifier = Modifier.weight(1f))

                BottomBarItem(
                    bottomBarItemType = BottomBarItemType.Forum,
                    selectedType = selectedType,
                    onClick = {
                        onClickBottomNavType(it)
                    },
                )

                Spacer(modifier = Modifier.weight(1f))

                BottomBarItem(
                    bottomBarItemType = BottomBarItemType.Article,
                    selectedType = selectedType,
                    onClick = {
                        onClickBottomNavType(it)
                    },
                )

                Spacer(modifier = Modifier.weight(1f))

                BottomBarItem(
                    bottomBarItemType = BottomBarItemType.Profile,
                    selectedType = selectedType,
                    onClick = {
                        onClickBottomNavType(it)
                    },
                )
            }
        }
    }
}


@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    bottomBarItemType: BottomBarItemType,
    selectedType: BottomBarItemType,
    onClick: (BottomBarItemType) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(IntrinsicSize.Max),
        contentAlignment = Alignment.Center
    ) {

        if (selectedType == bottomBarItemType) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(MaterialTheme.colorScheme.primary)
                )
            }
        }

        IconButton(
            onClick = {
                onClick(bottomBarItemType)
            },
            modifier = modifier
                .size(44.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
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
            DefaultBottomBar(
                selectedType = BottomBarItemType.Home,
                onClickBottomNavType = {},
            )
        }
    }
}