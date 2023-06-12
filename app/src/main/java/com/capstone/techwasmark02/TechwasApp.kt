package com.capstone.techwasmark02

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.capstone.techwasmark02.ui.navigation.Screen.*
import com.capstone.techwasmark02.ui.screen.article.ArticleScreen
import com.capstone.techwasmark02.ui.screen.camera.CameraScreen
import com.capstone.techwasmark02.ui.screen.catalog.CatalogScreen
import com.capstone.techwasmark02.ui.screen.catalogSingleComponent.CatalogSingleComponentScreen
import com.capstone.techwasmark02.ui.screen.detectionResult.DetectionResultScreen
import com.capstone.techwasmark02.ui.screen.home.HomeScreen
import com.capstone.techwasmark02.ui.screen.maps.MapsScreen
import com.capstone.techwasmark02.ui.screen.profileUser.ProfileUserScreen
import com.capstone.techwasmark02.ui.screen.setting.SettingScreen
import com.capstone.techwasmark02.ui.screen.singleArticle.SingleArticleScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechwasApp() {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Home.route,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            composable(Home.route) {
                HomeScreen(navController = navController)
            }

            composable(Article.route) {
                ArticleScreen(navController = navController)
            }

            composable(
                route = SingleArticle.route + "/{idArticle}",
                arguments = listOf(
                    navArgument("idArticle") {
                        type = NavType.IntType
                        defaultValue = 1
                    }
                )
            ) { navBackStackEntry ->
                val idArticle = navBackStackEntry.arguments?.getInt("idArticle")

                if(idArticle != null) {
                    SingleArticleScreen(idArticle = idArticle)
                }
            }

            composable(Forum.route) {

            }

            composable(Profile.route) {
                ProfileUserScreen(navController = navController)
            }

            composable(Catalog.route) {
                CatalogScreen(navController = navController)
            }

            composable(
                route = SingleCatalog.route + "/{componentJson}",
                arguments = listOf(
                    navArgument("componentJson") {
                        type = NavType.StringType
                        defaultValue = "U fucked up"
                    }
                )
            ) { navBackStackEntry ->
                val componentJson = navBackStackEntry.arguments?.getString("componentJson")

                if (componentJson != null) {
                    CatalogSingleComponentScreen(componentJson = componentJson)
                }
            }

            composable(Camera.route) {
                CameraScreen(navController = navController)
            }

            composable(
                route = DetectionResult.route + "/{uri}/{result}",
                arguments = listOf(
                    navArgument("uri") {
                        type = NavType.StringType
                        defaultValue = "Image to sent doesn't exist"
                    },
                    navArgument("result") {
                        type = NavType.StringType
                        defaultValue = "Nothing to predict"
                    }
                )
            ) { navBackStackEntry ->
                val uri = navBackStackEntry.arguments?.getString("uri")
                val result = navBackStackEntry.arguments?.getString("result")

                if (uri != null && result != null) {
                    DetectionResultScreen(stringUri = uri, detectionResult = result)
                }

            }

            composable(Maps.route) {
                MapsScreen(navController = navController)
            }

            composable(Setting.route) {
                SettingScreen(navController = navController)
            }
        }
    }
}