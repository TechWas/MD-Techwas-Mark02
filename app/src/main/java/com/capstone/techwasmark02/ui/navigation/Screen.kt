package com.capstone.techwasmark02.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

@OptIn(ExperimentalAnimationApi::class)
sealed class Screen  constructor(val route: String, val enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?), val exitTransition:  (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)) {

    object OnBoarding: Screen(
        route = "OnBoarding",
        enterTransition = {
            fadeIn(animationSpec = tween(700))
        },
        exitTransition = {
//            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            fadeOut(
                animationSpec = tween(700)
            )
        }
    )

    object SignIn: Screen(
        route = "SignIn",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
        }
    )

    object SignUp: Screen(
        route = "SignUp",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
        }
    )

    object Main: Screen(
        route = "Main",
        enterTransition = {
//            slideIntoContainer(
//                AnimatedContentScope.SlideDirection.Left,
//                animationSpec = tween(700)
//            )
            fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
//            slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(700))
            fadeOut(
                animationSpec = tween(700)
            )
        }
    )

    object Home: Screen(
        route = "home",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
//            slideOutOfContainer(
//                AnimatedContentScope.SlideDirection.Left, animationSpec = tween(
//                durationMillis = 700,
//            )
//            )
            fadeOut(
                animationSpec = tween(700)
            )
        }
    )

    object Forum: Screen(
        route = "forum",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left, animationSpec = tween(
                durationMillis = 700,
            )
            )
        }
    )

    object Profile: Screen(
        route = "profile",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left, animationSpec = tween(
                durationMillis = 700,
            )
            )
        }
    )

    object Setting: Screen(
        route = "setting",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left, animationSpec = tween(
                durationMillis = 700,
            )
            )
        }
    )

    object Camera: Screen(
        route = "camera",
        enterTransition = {
//            slideIntoContainer(
//                AnimatedContentScope.SlideDirection.Up,
//                animationSpec = tween(700)
//            )
            fadeIn(animationSpec = tween(1000))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(700))
        }
    )

    object Catalog: Screen(
        route = "catalog",
        enterTransition = {
//            slideIntoContainer(
//                AnimatedContentScope.SlideDirection.Left,
//                animationSpec = tween(700)
//            )
            fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
//            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            fadeOut(animationSpec = tween(300))
        }
    )

    object SingleCatalog: Screen(
        route = "singleCatalog",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
        }
    )

    object DetectionResult: Screen(
        route = "detectionResult",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
        }
    )

    object Article: Screen(
        route = "article",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
        }
    )

    object SingleArticle: Screen(
        route = "singleArticle",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
        }
    )

    object Splash: Screen(
        route = "splash",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
        }
    )

    object Maps: Screen(
        route = "maps",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
        }
    )
}
