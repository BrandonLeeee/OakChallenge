package com.brandon.oakchallenge.ui.navigation

sealed class Routes(val route: String) {
    object HomeScreen : Routes("home_screen")
    object ListScreen : Routes("list_screen")
}