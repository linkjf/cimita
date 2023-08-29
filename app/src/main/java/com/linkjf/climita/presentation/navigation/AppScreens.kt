package com.linkjf.climita.presentation.navigation

import com.linkjf.climita.presentation.ui.constants.Strings.LocationSearchScreenRoute
import com.linkjf.climita.presentation.ui.constants.Strings.SplashScreenRoute

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens(SplashScreenRoute)
    object LocationSearchScreen : AppScreens(LocationSearchScreenRoute)
}
