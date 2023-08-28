package com.linkjf.climita.remote.models

import com.google.gson.annotations.SerializedName

data class DayResponse(

    @field:SerializedName("avgtemp_c")
    val averageTemperature: Double,
    val condition: ConditionResponse,

    @field:SerializedName("maxtemp_c")
    val maxTemperature: Double,

    @field:SerializedName("mintemp_c")
    val minTemperature: Double
)
