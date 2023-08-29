package com.linkjf.climita.data.mapper

import com.linkjf.climita.data.models.ForecastEntity
import com.linkjf.climita.domain.models.Forecast
import javax.inject.Inject

class ForecastMapper @Inject constructor(
    private val currentMapper: CurrentMapper,
    private val forecastDayMapper: ForecastDayMapper,
    private val locationMapper: LocationMapper
) : Mapper<ForecastEntity, Forecast> {

    override fun mapFromEntity(entity: ForecastEntity): Forecast {
        return Forecast(
            current = currentMapper.mapFromEntity(entity.current),
            forecastList = forecastDayMapper.mapFromEntity(entity.forecastList),
            location = locationMapper.mapFromEntity(entity.location)
        )
    }

    override fun mapToEntity(model: Forecast): ForecastEntity {
        return ForecastEntity(
            current = currentMapper.mapToEntity(model.current),
            forecastList = forecastDayMapper.mapToEntity(model.forecastList),
            location = locationMapper.mapToEntity(model.location)
        )
    }
}
