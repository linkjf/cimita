package com.linkjf.climita.remote.models

import com.google.gson.annotations.SerializedName

data class CurrentResponse(
    val condition: ConditionResponse,
    @field:SerializedName("temp_c")
    val temperature: Double
)
