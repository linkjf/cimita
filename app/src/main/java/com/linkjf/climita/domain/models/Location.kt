package com.linkjf.climita.domain.models

data class Location(
    val country: String,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String)
