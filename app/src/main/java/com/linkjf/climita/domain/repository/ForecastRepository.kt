package com.linkjf.climita.domain.repository

import com.linkjf.climita.domain.models.Forecast
import com.linkjf.climita.remote.common.Result
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    suspend fun getForecast(location: String, days: Int): Flow<Result<Forecast>>
}
