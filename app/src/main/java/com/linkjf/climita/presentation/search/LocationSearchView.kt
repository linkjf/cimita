package com.linkjf.climita.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linkjf.climita.R
import com.linkjf.climita.domain.models.Location
import com.linkjf.climita.presentation.components.AutoCompleteUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSearchView(
    viewModel: LocationSearchViewModel = viewModel()
) {

    var cityName by remember {
        mutableStateOf("")
    }
    var query by remember {
        mutableStateOf("")
    }
    val predictions by viewModel.locationPredictions.observeAsState(initial = emptyList())

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
                painter = painterResource(id = R.drawable.ic_app_logo),
                contentDescription = "Climita Logo",
                modifier = Modifier
                    .height(60.dp)
                    .width(50.dp)
            )
            Row(
                modifier = Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                )
            ) {
                AutoCompleteUI<Location>(
                    modifier = Modifier
                        .fillMaxWidth(),
                    query = query,
                    queryLabel = stringResource(id = R.string.search_label),
                    onQueryChanged = { updatedQuery ->
                        query = updatedQuery
                        viewModel.getLocationPredictions(query)
                    },
                    predictions = predictions,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        textColor = Color.White,
                        placeholderColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedLabelColor = Color.White,

                        )
                ) {
                    Text(
                        text = "${it.name}, ${it.country}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)

                    )
                }
            }
        }
    }
}
