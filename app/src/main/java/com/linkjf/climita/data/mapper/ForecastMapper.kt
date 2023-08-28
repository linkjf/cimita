package com.linkjf.climita.data.mapper

import com.linkjf.climita.data.models.ConditionEntity
import com.linkjf.climita.data.models.CurrentEntity
import com.linkjf.climita.data.models.DayEntity
import com.linkjf.climita.data.models.ForecastDayEntity
import com.linkjf.climita.data.models.ForecastEntity
import com.linkjf.climita.domain.models.Condition
import com.linkjf.climita.domain.models.Current
import com.linkjf.climita.domain.models.Day
import com.linkjf.climita.domain.models.Forecast
import com.linkjf.climita.domain.models.ForecastDay
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

class CurrentMapper @Inject constructor(
    private val conditionMapper: ConditionMapper
) : Mapper<CurrentEntity, Current> {

    override fun mapFromEntity(entity: CurrentEntity): Current {
        return Current(
            temperature = entity.temperature,
            condition = conditionMapper.mapFromEntity(entity.condition)
        )
    }

    override fun mapToEntity(model: Current): CurrentEntity {
        return CurrentEntity(
            temperature = model.temperature,
            condition = conditionMapper.mapToEntity(model.condition)
        )
    }
}

class ConditionMapper @Inject constructor() :
    Mapper<ConditionEntity, Condition> {
    override fun mapFromEntity(entity: ConditionEntity): Condition {
        return Condition(
            code = entity.code,
            icon = entity.icon,
            text = entity.text
        )
    }

    override fun mapToEntity(model: Condition): ConditionEntity {
        return ConditionEntity(
            code = model.code,
            icon = model.icon,
            text = model.text
        )
    }
}

class ForecastDayMapper @Inject constructor(
    private val dayMapper: DayMapper
) : Mapper<List<ForecastDayEntity>, List<ForecastDay>> {

    override fun mapFromEntity(entity: List<ForecastDayEntity>): List<ForecastDay> {
        return entity.map {
            ForecastDay(
                date = it.date,
                day = dayMapper.mapFromEntity(it.day)
            )
        }
    }

    override fun mapToEntity(model: List<ForecastDay>): List<ForecastDayEntity> {
        return model.map {
            ForecastDayEntity(
                date = it.date,
                day = dayMapper.mapToEntity(it.day)
            )
        }
    }
}

class DayMapper @Inject constructor(
    private val conditionMapper: ConditionMapper
) : Mapper<DayEntity, Day> {

    override fun mapFromEntity(entity: DayEntity): Day {
        return Day(
            averageTemperature = entity.averageTemperature,
            condition = conditionMapper.mapFromEntity(entity.condition),
            maxTemperature = entity.maxTemperature,
            minTemperature = entity.minTemperature
        )
    }

    override fun mapToEntity(model: Day): DayEntity {
        return DayEntity(
            averageTemperature = model.averageTemperature,
            condition = conditionMapper.mapToEntity(model.condition),
            maxTemperature = model.maxTemperature,
            minTemperature = model.minTemperature
        )
    }
}
