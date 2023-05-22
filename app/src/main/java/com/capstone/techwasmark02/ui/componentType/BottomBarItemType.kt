package com.capstone.techwasmark02.ui.componentType

import com.capstone.techwasmark02.R

sealed class BottomBarItemType(val title: String, val icon: Int) {
    object Home: BottomBarItemType(title = "Home", icon = R.drawable.ic_home)
    object Forum: BottomBarItemType(title = "Forum", icon = R.drawable.ic_forum)
    object Article: BottomBarItemType(title = "Article", icon = R.drawable.ic_article)
    object Profile: BottomBarItemType(title = "Profile", icon = R.drawable.ic_profile)
}
