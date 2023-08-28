package com.linkjf.climita.data.models

data class DayEntity(
    val averageTemperature: Double,
    val condition: ConditionEntity,
    val maxTemperature: Double,
    val minTemperature: Double
)
