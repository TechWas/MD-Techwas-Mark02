package com.capstone.techwasmark02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.capstone.techwasmark02.ui.screen.article.ArticleScreen
import com.capstone.techwasmark02.ui.screen.catalog.CatalogScreen
import com.capstone.techwasmark02.ui.screen.home.HomeScreen
import com.capstone.techwasmark02.ui.screen.maps.MapsScreen
import com.capstone.techwasmark02.ui.theme.TechwasMark02Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechwasMark02Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TechwasApp()
//                    CatalogScreen()
                }
            }
        }
    }
}
