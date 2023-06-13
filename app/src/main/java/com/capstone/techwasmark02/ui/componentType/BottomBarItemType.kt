package com.capstone.techwasmark02.ui.componentType

import com.capstone.techwasmark02.R

sealed class BottomBarItemType(val title: String, val icon: Int, val pageIndex: Int) {
    object Home: BottomBarItemType(title = "Home", icon = R.drawable.ic_home, pageIndex = 0)
    object Forum: BottomBarItemType(title = "Forum", icon = R.drawable.ic_forum, pageIndex = 1)
    object Article: BottomBarItemType(title = "Article", icon = R.drawable.ic_article, pageIndex = 2)
    object Profile: BottomBarItemType(title = "Profile", icon = R.drawable.ic_profile, pageIndex = 3)
}
