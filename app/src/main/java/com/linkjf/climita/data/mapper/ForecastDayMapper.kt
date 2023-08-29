package com.linkjf.climita.data.mapper

import com.linkjf.climita.data.models.ForecastDayEntity
import com.linkjf.climita.domain.models.ForecastDay
import javax.inject.Inject

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
