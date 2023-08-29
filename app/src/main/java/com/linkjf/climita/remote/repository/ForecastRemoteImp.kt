package com.linkjf.climita.remote.repository

import com.linkjf.climita.BuildConfig
import com.linkjf.climita.data.models.ForecastEntity
import com.linkjf.climita.data.repository.ForecastRemote
import com.linkjf.climita.remote.api.ForecastService
import com.linkjf.climita.remote.common.Result
import com.linkjf.climita.remote.common.Result.Success
import com.linkjf.climita.remote.common.safeRequest
import com.linkjf.climita.remote.mapper.ForecastEntityMapper
import javax.inject.Inject

class ForecastRemoteImp @Inject constructor(
    private val forecastService: ForecastService,
    private val forecastEntityMapper: ForecastEntityMapper
) : ForecastRemote {

    override suspend fun getForecast(query: String, forecastDays: Int): Result<ForecastEntity> {
        val apiKey = BuildConfig.WEATHER_API_KEY
        val result = safeRequest {
            forecastService.getForecast(apiKey, query, forecastDays)
        }
        return if (result is Success) {
            Success(forecastEntityMapper.mapFromModel(result.data))
        } else
            Result.Error(-1, null)
    }
}
