package com.linkjf.climita.data.models

data class ForecastEntity(
    val current: CurrentEntity,
    val forecastList: List<ForecastDayEntity>,
    val location: LocationEntity
)

