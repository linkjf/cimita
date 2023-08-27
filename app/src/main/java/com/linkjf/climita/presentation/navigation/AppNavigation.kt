package com.linkjf.climita.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.linkjf.climita.presentation.search.LocationSearchView
import com.linkjf.climita.presentation.search.LocationSearchViewModel
import com.linkjf.climita.presentation.splash.SplashView


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {
        composable(AppScreens.SplashScreen.route) {
            SplashView(navController)
        }

        composable(AppScreens.LocationSearchScreen.route) {
            LocationSearchView(hiltViewModel<LocationSearchViewModel>())
        }
    }
}

