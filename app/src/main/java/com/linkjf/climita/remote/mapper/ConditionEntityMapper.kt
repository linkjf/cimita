package com.linkjf.climita.remote.mapper

import com.linkjf.climita.data.models.ConditionEntity
import com.linkjf.climita.remote.models.ConditionResponse
import javax.inject.Inject

class ConditionEntityMapper @Inject constructor() :
    EntityMapper<ConditionResponse, ConditionEntity> {
    override fun mapFromModel(model: ConditionResponse): ConditionEntity {
        return ConditionEntity(
            code = model.code,
            icon = model.icon,
            text = model.text
        )
    }
}
