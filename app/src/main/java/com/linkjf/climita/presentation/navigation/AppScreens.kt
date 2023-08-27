package com.linkjf.climita.presentation.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens("splash_screen")
    object LocationSearchScreen : AppScreens("location_search")
}
