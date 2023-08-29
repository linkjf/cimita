package com.linkjf.climita.data.mapper

import com.linkjf.climita.data.models.ConditionEntity
import com.linkjf.climita.domain.models.Condition
import javax.inject.Inject

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
