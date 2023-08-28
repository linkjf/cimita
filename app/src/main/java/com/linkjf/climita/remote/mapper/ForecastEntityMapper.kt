package com.linkjf.climita.remote.mapper

import com.linkjf.climita.data.models.ConditionEntity
import com.linkjf.climita.data.models.CurrentEntity
import com.linkjf.climita.data.models.DayEntity
import com.linkjf.climita.data.models.ForecastDayEntity
import com.linkjf.climita.data.models.ForecastEntity
import com.linkjf.climita.remote.models.ConditionResponse
import com.linkjf.climita.remote.models.CurrentResponse
import com.linkjf.climita.remote.models.DayResponse
import com.linkjf.climita.remote.models.ForecastListResponse
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

class CurrentEntityMapper @Inject constructor(
    private val conditionEntityMapper: ConditionEntityMapper
) : EntityMapper<CurrentResponse, CurrentEntity> {
    override fun mapFromModel(model: CurrentResponse): CurrentEntity {
        return CurrentEntity(
            temperature = model.temperature,
            condition = conditionEntityMapper.mapFromModel(model.condition)
        )
    }
}

class ConditionEntityMapper @Inject constructor() : EntityMapper<ConditionResponse, ConditionEntity> {
    override fun mapFromModel(model: ConditionResponse): ConditionEntity {
        return ConditionEntity(
            code = model.code,
            icon = model.icon,
            text = model.text
        )
    }
}

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

class DayEntityMapper @Inject constructor(
    private val conditionEntityMapper: ConditionEntityMapper
) : EntityMapper<DayResponse, DayEntity> {
    override fun mapFromModel(model: DayResponse): DayEntity {
        return DayEntity(
            averageTemperature = model.averageTemperature,
            condition = conditionEntityMapper.mapFromModel(model.condition),
            maxTemperature = model.maxTemperature,
            minTemperature = model.minTemperature
        )
    }
}
