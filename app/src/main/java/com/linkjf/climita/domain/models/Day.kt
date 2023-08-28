package com.linkjf.climita.domain.models

data class Day(
    val averageTemperature: Double,
    val condition: Condition,
    val maxTemperature: Double,
    val minTemperature: Double
)
