package com.capstone.techwasmark02.ui.componentType

import com.capstone.techwasmark02.R

sealed class SettingItemType(val title: String, val icon: Int) {

    object Password: SettingItemType(title = "Password and security", icon = R.drawable.ic_shield)

    object Comment: SettingItemType(title = "Comments you’ve posted", icon = R.drawable.ic_chat_buble)

    object Notification: SettingItemType(title = "Push Notifications", icon = R.drawable.ic_fill_notification)

    object Language: SettingItemType(title = "Language Preference", icon = R.drawable.ic_language)
}