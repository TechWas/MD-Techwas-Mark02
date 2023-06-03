package com.capstone.techwasmark02.ui.componentType

import androidx.compose.ui.graphics.Color
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.ui.theme.Green35
import com.capstone.techwasmark02.ui.theme.purple
import com.capstone.techwasmark02.ui.theme.sakura

sealed class FeatureBoxType(val title: String, val buttonTitle: String, val icon: Int, val backGround: Int, val buttonColor: Color) {
    object Detection: FeatureBoxType(title = "Detect e-waste", buttonTitle = "Detect", icon = R.drawable.ic_center_focus, backGround = R.drawable.img_bg_green, buttonColor = Green35)
    object Catalog: FeatureBoxType(title = "E-waste catalog", buttonTitle = "Open", icon = R.drawable.ic_menu_book, backGround = R.drawable.img_bg_purple, buttonColor = purple)
    object DropPoint: FeatureBoxType(title = "Nearby drop point", buttonTitle = "Locate", icon = R.drawable.ic_location_on, backGround = R.drawable.img_bg_sakura, buttonColor = sakura)
}