package com.capstone.techwasmark02

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.capstone.techwasmark02.ui.navigation.Screen.*
import com.capstone.techwasmark02.ui.screen.article.ArticleScreen
import com.capstone.techwasmark02.ui.screen.camera.CameraScreen
import com.capstone.techwasmark02.ui.screen.catalog.CatalogScreen
import com.capstone.techwasmark02.ui.screen.catalogSingleComponent.CatalogSingleComponentScreen
import com.capstone.techwasmark02.ui.screen.detectionResult.DetectionResultScreen
import com.capstone.techwasmark02.ui.screen.forum.ForumScreen
import com.capstone.techwasmark02.ui.screen.forumSingle.ForumSingleScreen
import com.capstone.techwasmark02.ui.screen.home.HomeScreen
import com.capstone.techwasmark02.ui.screen.main.MainScreen
import com.capstone.techwasmark02.ui.screen.maps.MapsScreen
import com.capstone.techwasmark02.ui.screen.onBoarding.OnBoardingScreen
import com.capstone.techwasmark02.ui.screen.profileUser.ProfileUserScreen
import com.capstone.techwasmark02.ui.screen.setting.SettingScreen
import com.capstone.techwasmark02.ui.screen.signIn.SignInScreen
import com.capstone.techwasmark02.ui.screen.signUp.SignUpScreen
import com.capstone.techwasmark02.ui.screen.singleArticle.SingleArticleScreen
import com.capstone.techwasmark02.ui.screen.splashScreen.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun TechwasApp() {
    val navController = rememberAnimatedNavController()

    Scaffold { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = Splash.route,
            modifier = Modifier
                .padding(innerPadding),

            ) {

            composable(
                route = Splash.route,
                enterTransition = Splash.enterTransition,
                exitTransition = Splash.exitTransition
            ) {
                SplashScreen(navController = navController)
            }

            composable(
                route = OnBoarding.route,
                enterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                exitTransition = OnBoarding.exitTransition
            ) {
                OnBoardingScreen(navController = navController)
            }

            composable(SignIn.route) {
                SignInScreen(navController = navController)
            }

            composable(SignUp.route) {
                SignUpScreen(navController = navController)
            }

            composable(
                route = Main.route,
                enterTransition = Main.enterTransition,
                exitTransition = Main.exitTransition
            ) {
                MainScreen(navController = navController)
            }

            composable(
                route = Home.route,
                enterTransition = Home.enterTransition,
                exitTransition = Home.exitTransition
            ) {
                HomeScreen(navController = navController)
            }

            composable(
                route = Article.route,
                enterTransition = Article.enterTransition,
                exitTransition = Article.exitTransition
            ) {
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
                    SingleArticleScreen(idArticle = idArticle, navController = navController)
                }
            }

            composable(Forum.route) {
                ForumScreen(navController = navController)
            }

            composable(SingleForum.route) {
                ForumSingleScreen(navController = navController)
            }

            composable(Profile.route) {
                ProfileUserScreen(navController = navController)
            }
            
            composable(
                route = Setting.route,
                enterTransition = Setting.enterTransition,
                exitTransition = Setting.exitTransition
            ) {
                SettingScreen(navController = navController)
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
                    CatalogSingleComponentScreen(componentJson = componentJson, navController = navController)
                }
            }

            composable(
                route = Camera.route,
                enterTransition = Camera.enterTransition,
                exitTransition = Camera.exitTransition
            ) {
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
                    DetectionResultScreen(stringUri = uri, detectionResult = result, navController = navController)
                }

            }

            composable(Maps.route) {
                MapsScreen(navController = navController)
            }
        }
    }
}