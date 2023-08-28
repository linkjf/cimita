package com.linkjf.climita.remote.models

import com.google.gson.annotations.SerializedName

data class ForecastListResponse(
    @field:SerializedName("forecastday")
    val forecast: List<ForecastDayResponse>
)
