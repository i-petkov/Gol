package com.example.gol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gol.ui.screens.GolScreen
import com.example.gol.ui.screens.Routes
import com.example.gol.ui.screens.SettingsScreen
import com.example.gol.ui.screens.SettingsScreenViewModel
import com.example.gol.ui.theme.GoLTheme

class MainActivity : ComponentActivity() {

    private val golViewModel: GolViewModel by viewModels()
    private val settingsViewModel: SettingsScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoLTheme {
//                val widthSizeClass: WindowWidthSizeClass = calculateWindowSizeClass(this).widthSizeClass

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Routes.Gol.route) {
                    composable(Routes.Gol.route) {
                        GolScreen(onSettingsClicked = {
                                navController.navigate(Routes.Settings.route)
                            }, golViewModel)
                    }
                    composable(Routes.Settings.route) {
//                        val parentEntry = remember(it) {
//                            navController.getBackStackEntry(Routes.Gol.route)
//                        }
                        SettingsScreen(onBackPressed = {
                            navController.popBackStack()
                        }, settingsViewModel)
                    }
                }
            }
        }
    }
}