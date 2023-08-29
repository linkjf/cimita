package com.linkjf.climita.remote.repository

import com.linkjf.climita.data.models.ForecastEntity
import com.linkjf.climita.data.repository.ForecastRemote
import com.linkjf.climita.remote.api.ForecastService
import com.linkjf.climita.remote.common.Result
import com.linkjf.climita.remote.common.Result.Error
import com.linkjf.climita.remote.common.Result.Exception
import com.linkjf.climita.remote.common.Result.Success
import com.linkjf.climita.remote.common.safeRequest
import com.linkjf.climita.remote.mapper.ForecastEntityMapper
import javax.inject.Inject

class ForecastRemoteImp @Inject constructor(
    private val forecastService: ForecastService,
    private val forecastEntityMapper: ForecastEntityMapper
) : ForecastRemote {

    override suspend fun getForecast(query: String, forecastDays: Int): Result<ForecastEntity> {
        val result = safeRequest {
            forecastService.getForecast(query, forecastDays)
        }

        return when (result) {
            is Success -> {
                Success(forecastEntityMapper.mapFromModel(result.data))
            }
            is Error -> Error(code = result.code, message = result.message)
            is Exception -> Exception(e = result.e)
        }
    }
}
