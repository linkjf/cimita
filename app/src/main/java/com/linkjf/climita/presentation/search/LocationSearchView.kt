package com.linkjf.climita.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
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

    Column {
        Row {
            AutoCompleteUI<Location>(
                modifier = Modifier.fillMaxWidth(),
                query = query,
                queryLabel = "Search your destiny",
                onQueryChanged = { updatedQuery ->
                    query = updatedQuery
                    viewModel.getLocationPredictions(query)
                },
                predictions = predictions
            ) {
                Text(it.name)
            }
        }
    }
}
