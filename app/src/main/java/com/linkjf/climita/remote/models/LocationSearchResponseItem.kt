package com.linkjf.climita.remote.models

data class LocationSearchResponseItem(
    val country: String,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val url: String
)
