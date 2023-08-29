package com.linkjf.climita.data.mapper

import com.linkjf.climita.data.models.CurrentEntity
import com.linkjf.climita.domain.models.Current
import javax.inject.Inject

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
