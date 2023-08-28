package com.linkjf.climita.data.repository

import com.linkjf.climita.data.models.ForecastEntity
import com.linkjf.climita.remote.common.Result

interface ForecastDataSource {
    suspend fun getForecast(location: String, days: Int): Result<ForecastEntity>
}
