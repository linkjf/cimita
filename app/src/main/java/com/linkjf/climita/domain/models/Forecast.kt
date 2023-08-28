package com.linkjf.climita.domain.models

data class Forecast(
    val current: Current,
    val forecastList: List<ForecastDay>,
    val location: Location
)

