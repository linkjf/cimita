package com.linkjf.climita.data.mapper

import com.linkjf.climita.data.models.DayEntity
import com.linkjf.climita.domain.models.Day
import javax.inject.Inject

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
