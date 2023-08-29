package com.linkjf.climita.data.repository

import com.linkjf.climita.data.mapper.ForecastMapper
import com.linkjf.climita.data.source.ForecastRemoteDataSource
import com.linkjf.climita.domain.models.Forecast
import com.linkjf.climita.domain.repository.ForecastRepository
import com.linkjf.climita.remote.common.Result
import com.linkjf.climita.remote.common.Result.Error
import com.linkjf.climita.remote.common.Result.Exception
import com.linkjf.climita.remote.common.Result.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ForecastRepositoryImp @Inject constructor(
    private val forecastRemoteDataSource: ForecastRemoteDataSource,
    private val mapper: ForecastMapper
) : ForecastRepository {
    override suspend fun getForecast(location: String, days: Int): Flow<Result<Forecast>> =
        flow {


            when (val result = forecastRemoteDataSource.getForecast(location, days)) {
                is Success -> {
                    val forecast = mapper.mapFromEntity(result.data)
                    emit(Success(forecast))
                }

                is Error -> emit(Error(code = result.code, message = result.message))
                is Exception -> emit(Exception(e = result.e))
            }
        }

}
