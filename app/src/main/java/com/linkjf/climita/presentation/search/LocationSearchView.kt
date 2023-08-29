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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linkjf.climita.R
import com.linkjf.climita.domain.models.Location
import com.linkjf.climita.presentation.components.AutoCompleteUI
import com.linkjf.climita.presentation.forecast.ForecastView
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LocationSearchView(
    viewModel: LocationSearchViewModel = viewModel()
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    var selectedIndex by remember { mutableIntStateOf(0) }

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
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_app_icon_transparent),
                contentDescription = "Climita Logo",
                modifier = Modifier
                    .height(60.dp)
                    .width(50.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    )
            ) {

                Column(
                    modifier = Modifier.padding(top = 120.dp)
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
                                .padding(8.dp)
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
                .padding(vertical = 8.dp, horizontal = 10.dp)
                .background(Color.Transparent)

        )
}

@Composable
private fun ShowError(show: Boolean) {
    if (show)
        Snackbar(
            modifier = Modifier
                .padding(all = 8.dp),
            containerColor = Color.Red
        ) {
            Text(
                text = stringResource(id = R.string.error_label),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp)
            )
        }
}
