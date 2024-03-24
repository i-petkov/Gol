package com.example.gol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gol.ui.screens.gol.GolScreen
import com.example.gol.ui.screens.Routes
import com.example.gol.ui.screens.settings.SettingsScreen
import com.example.gol.ui.screens.settings.SettingsScreenViewModel
import com.example.gol.ui.screens.gol.GolViewModel
import com.example.gol.ui.theme.GoLTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoLTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Routes.Gol.route) {
                    composable(Routes.Gol.route) {
                        GolScreen(onSettingsClicked = {
                                navController.navigate(Routes.Settings.route)
                            }, viewModel<GolViewModel>())
                    }
                    composable(Routes.Settings.route) {
                        SettingsScreen(onBackPressed = {
                            navController.popBackStack()
                        }, viewModel<SettingsScreenViewModel>())
                    }
                }
            }
        }
    }
}