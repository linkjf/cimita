package com.linkjf.climita.data.source

import com.linkjf.climita.data.models.ForecastEntity
import com.linkjf.climita.data.repository.ForecastDataSource
import com.linkjf.climita.data.repository.ForecastRemote
import com.linkjf.climita.remote.common.Result
import javax.inject.Inject

class ForecastRemoteDataSource @Inject constructor(
    private val forecastRemote: ForecastRemote
) : ForecastDataSource {
    override suspend fun getForecast(location: String, days: Int): Result<ForecastEntity> {
        return forecastRemote.getForecast(location, days)
    }

}
