package com.linkjf.climita.remote.mapper

import com.linkjf.climita.data.models.ForecastDayEntity
import com.linkjf.climita.remote.models.ForecastListResponse
import javax.inject.Inject

class ForecastDayEntityMapper @Inject constructor(
    private val dayEntityMapper: DayEntityMapper
) : EntityMapper<ForecastListResponse, List<ForecastDayEntity>> {
    override fun mapFromModel(model: ForecastListResponse): List<ForecastDayEntity> {
        return model.forecast.map { forecastDayResponse ->
            ForecastDayEntity(
                date = forecastDayResponse.date,
                day = dayEntityMapper.mapFromModel(forecastDayResponse.day)
            )
        }
    }
}
