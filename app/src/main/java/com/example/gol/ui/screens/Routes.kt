package com.example.gol.ui.screens

sealed class Routes(val route: String) {
    object Gol : Routes("gol")
    object Settings : Routes("settings")
}