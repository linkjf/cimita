package com.linkjf.climita.remote.mapper

import com.linkjf.climita.data.models.ForecastEntity
import com.linkjf.climita.remote.models.ForecastResponse
import javax.inject.Inject

class ForecastEntityMapper @Inject constructor(
    private val currentEntityMapper: CurrentEntityMapper,
    private val forecastDayEntityMapper: ForecastDayEntityMapper,
    private val locationEntityMapper: LocationEntityMapper
) : EntityMapper<ForecastResponse, ForecastEntity> {
    override fun mapFromModel(model: ForecastResponse): ForecastEntity {
        return ForecastEntity(
            current = currentEntityMapper.mapFromModel(model.current),
            forecastList = forecastDayEntityMapper.mapFromModel(model.forecast),
            location = locationEntityMapper.mapFromModel(model.location)
        )
    }
}
