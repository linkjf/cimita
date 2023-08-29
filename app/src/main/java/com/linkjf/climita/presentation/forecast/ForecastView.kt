package com.linkjf.climita.presentation.forecast

import android.text.format.DateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.linkjf.climita.R
import com.linkjf.climita.domain.models.Forecast
import com.linkjf.climita.domain.models.ForecastDay
import com.linkjf.climita.presentation.ui.constants.Dimens.conditionContainerHorizontalPadding
import com.linkjf.climita.presentation.ui.constants.Dimens.conditionIconSelectedSize
import com.linkjf.climita.presentation.ui.constants.Dimens.conditionIconUnSelectedSize
import com.linkjf.climita.presentation.ui.constants.Dimens.countryBottomPadding
import com.linkjf.climita.presentation.ui.constants.Dimens.countryTopPadding
import com.linkjf.climita.presentation.ui.constants.Dimens.forecastDayHorizontalPadding
import com.linkjf.climita.presentation.ui.constants.Dimens.forecastVerticalPadding
import com.linkjf.climita.presentation.ui.constants.Dimens.tempContainerPadding
import com.linkjf.climita.presentation.ui.constants.Strings.forecastDateFormat
import com.linkjf.climita.presentation.ui.constants.Strings.forecastDayDateFormat
import com.linkjf.climita.presentation.ui.constants.Strings.temperatureFormat
import com.linkjf.climita.presentation.ui.theme.white20
import com.linkjf.climita.presentation.ui.theme.white50
import java.text.SimpleDateFormat


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ForecastView(
    forecast: Forecast,
) {
    val dateFormat = SimpleDateFormat(forecastDateFormat)
    var selectedIndex by remember { mutableIntStateOf(0) }
    val lazyListState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            forecast.location.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
        )
        Text(
            forecast.location.country,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(top = countryTopPadding, bottom = countryBottomPadding)
        )

        LazyColumn(
            state = lazyListState,
            modifier = Modifier,
        ) {
            items(forecast.forecastList.size) { forecastDayIndex ->
                ForecastItemView(
                    forecast.forecastList[forecastDayIndex],
                    selectedIndex == forecastDayIndex,
                    dateFormat,
                    forecastDayIndex
                ) { itemIndex ->
                    selectedIndex = itemIndex
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
private fun ForecastItemView(
    forecastDay: ForecastDay,
    isSelected: Boolean,
    dateFormat: SimpleDateFormat,
    index: Int,
    onClick: (Int) -> Unit
) {

    val alpha = if (isSelected) 1f else 0.2f
    val color = if (isSelected) white50 else white20

    val dayTextStyle =
        if (isSelected)
            MaterialTheme.typography.titleMedium
        else
            MaterialTheme.typography.bodyMedium

    val avgTempTextStyle =
        if (isSelected)
            MaterialTheme.typography.displayLarge
        else
            MaterialTheme.typography.headlineLarge

    val minMaxTemTextStyle =
        if (isSelected)
            MaterialTheme.typography.bodySmall
        else
            MaterialTheme.typography.labelSmall

    val conditionTextStyle =
        if (isSelected)
            MaterialTheme.typography.bodySmall
        else
            MaterialTheme.typography.labelSmall

    val conditionSize =
        if (isSelected)
            conditionIconSelectedSize
        else
            conditionIconUnSelectedSize

    Row(
        modifier = Modifier
            .padding(vertical = forecastVerticalPadding)
            .fillMaxWidth()
            .background(color)
            .alpha(alpha)
            .selectable(isSelected,
                onClick = {
                    onClick.invoke(index)
                }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start

    ) {
        val date = dateFormat.parse(forecastDay.date)
        val dayString = DateFormat.format(forecastDayDateFormat, date).toString().uppercase()
        Text(
            text = dayString,
            style = dayTextStyle,
            modifier = Modifier.padding(horizontal = forecastDayHorizontalPadding)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = tempContainerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val avgTemp =
                String.format(temperatureFormat, forecastDay.day.averageTemperature.toInt())

            val minTemp =
                String.format(temperatureFormat, forecastDay.day.minTemperature.toInt())

            val maxTemp =
                String.format(temperatureFormat, forecastDay.day.maxTemperature.toInt())

            Text(
                text = avgTemp,
                style = avgTempTextStyle,
                modifier = Modifier
            )
            Text(
                text = "${minTemp}/${maxTemp}",
                style = minMaxTemTextStyle,
                modifier = Modifier
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = conditionContainerHorizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GlideImage(
                model = "https:${forecastDay.day.condition.icon}",
                contentDescription = stringResource(id = R.string.condition_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(conditionSize)
                    .height(conditionSize)
            )
            Text(
                text = forecastDay.day.condition.text,
                style = conditionTextStyle,
                maxLines = 2,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        }
    }
}
