package com.linkjf.climita.data.repository

import com.linkjf.climita.data.models.ForecastEntity
import com.linkjf.climita.remote.common.Result

interface ForecastRemote {
    suspend fun getForecast(query: String, forecastDays: Int): Result<ForecastEntity>
}
