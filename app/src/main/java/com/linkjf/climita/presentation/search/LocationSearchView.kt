package com.linkjf.climita.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linkjf.climita.R
import com.linkjf.climita.domain.models.Location
import com.linkjf.climita.presentation.components.AutoCompleteUI
import com.linkjf.climita.presentation.forecast.ForecastView
import com.linkjf.climita.presentation.ui.constants.Dimens.emptyResultTextHorizontalPadding
import com.linkjf.climita.presentation.ui.constants.Dimens.emptyResultTextVerticalPadding
import com.linkjf.climita.presentation.ui.constants.Dimens.errorSnackBarPadding
import com.linkjf.climita.presentation.ui.constants.Dimens.errorTextPadding
import com.linkjf.climita.presentation.ui.constants.Dimens.forecastContainerTopPadding
import com.linkjf.climita.presentation.ui.constants.Dimens.searchAppLogoHeight
import com.linkjf.climita.presentation.ui.constants.Dimens.searchAppLogoWidth
import com.linkjf.climita.presentation.ui.constants.Dimens.searchContainerHorizontalPadding
import com.linkjf.climita.presentation.ui.constants.Dimens.searchContainerSpacerHeight
import com.linkjf.climita.presentation.ui.constants.Dimens.searchContainerVerticalPadding
import com.linkjf.climita.presentation.ui.constants.Dimens.searchResultPadding

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LocationSearchView(
    viewModel: LocationSearchViewModel = viewModel()
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    val locationQuery by viewModel.query.observeAsState("")
    val showLoading by viewModel.isLoading.observeAsState(initial = false)
    val showEmpty by viewModel.showEmpty.observeAsState(initial = false)
    val showError by viewModel.showError.observeAsState(initial = false)

    val predictions by viewModel.locationPredictions.observeAsState(initial = emptyList())
    val forecast by viewModel.forecast.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.bg_app_background),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(searchContainerSpacerHeight))
            Image(
                painter = painterResource(id = R.drawable.ic_app_icon_transparent),
                contentDescription = stringResource(id = R.string.app_logo_description),
                modifier = Modifier
                    .height(searchAppLogoHeight)
                    .width(searchAppLogoWidth)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = searchContainerHorizontalPadding,
                        vertical = searchContainerVerticalPadding
                    )
            ) {

                Column(
                    modifier = Modifier.padding(top = forecastContainerTopPadding)
                ) {
                    forecast?.let {
                        ForecastView(forecast = it)
                    }
                }

                Column() {
                    AutoCompleteUI<Location>(
                        modifier = Modifier
                            .fillMaxWidth(),
                        query = locationQuery,
                        queryLabel = stringResource(id = R.string.search_label),
                        onQueryChanged = { updatedQuery ->
                            viewModel.getLocationPredictions(updatedQuery)
                        },
                        predictions = predictions,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.White,
                            textColor = Color.White,
                            placeholderColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            focusedLabelColor = Color.White,
                        ),
                        onClearClick = {
                            viewModel.clear()
                        },
                        onDoneActionClick = {
                            keyboardController?.hide()
                        },
                        onItemClick = {
                            keyboardController?.hide()
                            viewModel.clear()
                            viewModel.getForecast(it)
                        }
                    ) {

                        Text(
                            text = "${it.name}, ${it.country}",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(searchResultPadding)
                        )
                    }

                    ShowEmptyResult(showEmpty)
                    ShowError(show = showError)
                }
            }
        }
    }
}

@Composable
private fun ShowEmptyResult(show: Boolean) {
    if (show)
        Text(
            text = stringResource(id = R.string.empty_label),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = emptyResultTextVerticalPadding,
                    horizontal = emptyResultTextHorizontalPadding
                )
                .background(Color.Transparent)

        )
}

@Composable
private fun ShowError(show: Boolean) {
    if (show)
        Snackbar(
            modifier = Modifier
                .padding(all = errorSnackBarPadding),
            containerColor = Color.Red
        ) {
            Text(
                text = stringResource(id = R.string.error_label),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = errorTextPadding)
            )
        }
}
