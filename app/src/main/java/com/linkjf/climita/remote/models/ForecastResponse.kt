package com.linkjf.climita.remote.models

data class ForecastResponse(
    val current: CurrentResponse,
    val forecast: ForecastListResponse,
    val location: LocationResponse
)
