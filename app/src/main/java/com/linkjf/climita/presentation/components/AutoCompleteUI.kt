package com.linkjf.climita.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.linkjf.climita.presentation.ui.constants.Dimens.autoCompleteCornerSize
import com.linkjf.climita.presentation.ui.constants.Dimens.autocompleteSeparator
import com.linkjf.climita.presentation.ui.constants.Dimens.locationSeparatorThickness
import com.linkjf.climita.presentation.ui.constants.Dimens.minHeightMultiplier
import com.linkjf.climita.presentation.ui.theme.grey
import com.linkjf.climita.presentation.ui.theme.grey80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AutoCompleteUI(
    modifier: Modifier,
    query: String,
    queryLabel: String,
    colors: TextFieldColors? = null,
    onQueryChanged: (String) -> Unit = {},
    predictions: List<T>,
    onDoneActionClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onItemClick: (T) -> Unit = {},
    itemContent: @Composable (T) -> Unit = {}
) {

    val view = LocalView.current
    val lazyListState = rememberLazyListState()

    Column {

        QuerySearch(
            query = query,
            label = queryLabel,
            colors = colors,
            onQueryChanged = onQueryChanged,
            onDoneActionClick = {
                view.clearFocus()
                onDoneActionClick()
            },
            onClearClick = {
                onClearClick()
            }
        )
        Divider(color = Color.Transparent, thickness = autocompleteSeparator)
        LazyColumn(
            state = lazyListState,
            modifier = modifier.heightIn(max = TextFieldDefaults.MinHeight * minHeightMultiplier)
        ) {
            if (predictions.isNotEmpty()) {
                items(predictions.size) { predictionIndex ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(grey)
                            .clickable {
                                view.clearFocus()
                                onItemClick(predictions[predictionIndex])
                            }
                    ) {
                        itemContent(predictions[predictionIndex])
                    }
                    Divider(color = grey80, thickness = locationSeparatorThickness)
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuerySearch(
    modifier: Modifier = Modifier,
    query: String,
    label: String,
    colors: TextFieldColors?,
    onDoneActionClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onQueryChanged: (String) -> Unit
) {

    var showClearButton by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                showClearButton = focusState.isFocused
            },
        shape = RoundedCornerShape(autoCompleteCornerSize),
        value = query,
        onValueChange = onQueryChanged,
        label = { Text(text = label) },
        textStyle = MaterialTheme.typography.displaySmall,
        singleLine = true,
        trailingIcon = {
            if (showClearButton) {
                IconButton(onClick = {
                    onClearClick()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        tint = Color.White,
                        contentDescription = "Clear"
                    )
                }
            }
        },
        keyboardActions = KeyboardActions(onDone = {
            onDoneActionClick()
        }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        colors = colors ?: TextFieldDefaults.outlinedTextFieldColors()
    )

}
