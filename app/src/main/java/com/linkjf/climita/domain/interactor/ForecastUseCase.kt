package com.linkjf.climita.domain.interactor

import com.linkjf.climita.domain.models.Forecast
import com.linkjf.climita.domain.repository.ForecastRepository
import com.linkjf.climita.remote.common.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias ForecastBaseUseCase = BaseUseCase<Pair<String, Int>, Flow<Result<Forecast>>>

class ForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository
) : ForecastBaseUseCase {
    override suspend fun invoke(parameter: Pair<String, Int>): Flow<Result<Forecast>> =
        forecastRepository.getForecast(parameter.first, parameter.second)
}
