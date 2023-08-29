package com.linkjf.climita.remote.mapper

import com.linkjf.climita.data.models.CurrentEntity
import com.linkjf.climita.remote.models.CurrentResponse
import javax.inject.Inject

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
