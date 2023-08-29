package com.linkjf.climita.remote.mapper

import com.linkjf.climita.data.models.DayEntity
import com.linkjf.climita.remote.models.DayResponse
import javax.inject.Inject

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
