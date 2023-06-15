package com.capstone.techwasmark02.ui.screen.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.capstone.techwasmark02.ui.component.DefaultBottomBar
import com.capstone.techwasmark02.ui.componentType.BottomBarItemType
import com.capstone.techwasmark02.ui.screen.article.ArticleScreen
import com.capstone.techwasmark02.ui.screen.forum.ForumScreen
import com.capstone.techwasmark02.ui.screen.home.HomeScreen
import com.capstone.techwasmark02.ui.screen.profileUser.ProfileUserScreen

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainScreenViewModel = viewModel(),
    page: Int
) {

    val selectedBottomBarType by viewModel.selectedBottomBarType.collectAsState()
    val activity = LocalContext.current as Activity


    BackHandler(
        enabled = true,
        onBack = {
            if(selectedBottomBarType.pageIndex != 0) {
                viewModel.updateSelectedBottomBartype(BottomBarItemType.Home)
            } else {
                activity.finish()
            }
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        LaunchedEffect(Unit) {
            when(page) {
                0 -> {
                    viewModel.updateSelectedBottomBartype(BottomBarItemType.Home)
                }
                1 -> {
                    viewModel.updateSelectedBottomBartype(BottomBarItemType.Forum)
                }
                2 -> {
                    viewModel.updateSelectedBottomBartype(BottomBarItemType.Article)
                }
                3 -> {
                    viewModel.updateSelectedBottomBartype(BottomBarItemType.Profile)
                }
            }
        }

        when(selectedBottomBarType) {
            is BottomBarItemType.Home -> {
                HomeScreen(navController = navController)
            }
            is BottomBarItemType.Forum -> {
                ForumScreen(navController = navController)
            }
            is BottomBarItemType.Article -> {
                ArticleScreen(navController = navController)
            }
            is BottomBarItemType.Profile -> {
                ProfileUserScreen(navController = navController)
            }
        }

        DefaultBottomBar(
            selectedType = selectedBottomBarType,
            onClickBottomNavType = {
                viewModel.updateSelectedBottomBartype(it)
            },
        )
    }
}