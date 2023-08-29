package com.linkjf.climita.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.linkjf.climita.R
import com.linkjf.climita.presentation.navigation.AppScreens
import com.linkjf.climita.presentation.ui.constants.Dimens.splashLogoHeight
import com.linkjf.climita.presentation.ui.constants.Dimens.splashLogoWidth
import kotlinx.coroutines.delay

@Composable
fun SplashView(navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(1500)
        navController.popBackStack()
        navController.navigate(AppScreens.LocationSearchScreen.route)
    }
    Splash()
}

@Composable
fun Splash() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_app_background),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )

        Image(
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = "Climita Logo",
            modifier = Modifier
                .height(splashLogoHeight)
                .width(splashLogoWidth)
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowPreview() {
    Splash()
}

