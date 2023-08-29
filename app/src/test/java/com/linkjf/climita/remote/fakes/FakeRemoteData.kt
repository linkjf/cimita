package com.linkjf.climita.remote.fakes

import com.linkjf.climita.data.models.ConditionEntity
import com.linkjf.climita.data.models.CurrentEntity
import com.linkjf.climita.data.models.DayEntity
import com.linkjf.climita.data.models.ForecastDayEntity
import com.linkjf.climita.data.models.ForecastEntity
import com.linkjf.climita.data.models.LocationEntity
import com.linkjf.climita.domain.models.Condition
import com.linkjf.climita.domain.models.Current
import com.linkjf.climita.domain.models.Day
import com.linkjf.climita.domain.models.Forecast
import com.linkjf.climita.domain.models.ForecastDay
import com.linkjf.climita.domain.models.Location
import com.linkjf.climita.remote.models.ConditionResponse
import com.linkjf.climita.remote.models.CurrentResponse
import com.linkjf.climita.remote.models.DayResponse
import com.linkjf.climita.remote.models.ForecastDayResponse
import com.linkjf.climita.remote.models.ForecastListResponse
import com.linkjf.climita.remote.models.ForecastResponse
import com.linkjf.climita.remote.models.LocationResponse
import com.linkjf.climita.remote.models.LocationSearchResponse

object FakeRemoteData {

    const val fakeQuery = ""
    const val fakeDays = 3

    fun getFakeLocationSearchResponse(): LocationSearchResponse {

        val locationSearchResponse = LocationSearchResponse()
        locationSearchResponse.add(getFakeLocationResponse())
        locationSearchResponse.add(getFakeLocationResponse())
        return locationSearchResponse
    }

    fun getFakeForecastResponse() =
        ForecastResponse(
            current = getFakeCurrentResponse(),
            forecast = getFakeForecastListResponse(),
            location = getFakeLocationResponse()
        )

    fun getFakeCurrentResponse() =
        CurrentResponse(
            condition = getFakeConditionResponse(),
            temperature = 40.0
        )

    fun getFakeForecastListResponse(): ForecastListResponse {
        val forecastDay = getFakeForecastDayResponse()
        val forecastList = listOf(forecastDay, forecastDay)
        return ForecastListResponse(
            forecast = forecastList
        )

    }

    fun getFakeForecastDayResponse() = ForecastDayResponse(
        date = "2023-08-28",
        day = getFakeDayResponse()
    )

    fun getFakeDayResponse() = DayResponse(
        averageTemperature = 40.0,
        condition = getFakeConditionResponse(),
        maxTemperature = 50.0,
        minTemperature = 20.0
    )

    fun getFakeConditionResponse() = ConditionResponse(
        code = 1,
        icon = "icon",
        text = "Condition"
    )

    fun getFakeLocationResponse() =
        LocationResponse(
            country = "Colombia",
            id = 123,
            lat = 12.0,
            lon = 12.0,
            name = "Cartagena",
            region = "America",
            url = "cartagena"
        )

    fun getFakeForecastEntity() =
        ForecastEntity(
            current = getFakeCurrentEntity(),
            forecastList = getFakeForecastEntityList(),
            location = getFakeLocationEntity()
        )

    fun getFakeCurrentEntity() =
        CurrentEntity(
            condition = getFakeConditionEntity(),
            temperature = 40.0
        )

    fun getFakeForecastEntityList(): List<ForecastDayEntity> {
        val forecastDay = getFakeForecastDayEntity()
        return listOf(forecastDay, forecastDay)
    }

    fun getFakeForecastDayEntity() = ForecastDayEntity(
        date = "2023-08-28",
        day = getFakeDayEntity()
    )

    fun getFakeDayEntity() = DayEntity(
        averageTemperature = 40.0,
        condition = getFakeConditionEntity(),
        maxTemperature = 50.0,
        minTemperature = 20.0
    )

    fun getFakeConditionEntity() = ConditionEntity(
        code = 1,
        icon = "icon",
        text = "Condition"
    )

    fun getFakeLocationEntityList(): List<LocationEntity> {
        val locationEntity = getFakeLocationEntity()
        return listOf(locationEntity, locationEntity)
    }

    fun getFakeLocationEntity() =
        LocationEntity(
            country = "Colombia",
            id = 123,
            lat = 12.0,
            lon = 12.0,
            name = "Cartagena",
            region = "America"
        )

    fun getFakeLocation() = Location(
        country = "Colombia",
        id = 123,
        lat = 12.0,
        lon = 12.0,
        name = "Cartagena",
        region = "America"
    )

    fun getFakeCondition() = Condition(
        code = 1,
        icon = "icon",
        text = "Condition"
    )

    fun getFakeDay() = Day(
        averageTemperature = 40.0,
        condition = getFakeCondition(),
        maxTemperature = 50.0,
        minTemperature = 20.0
    )

    fun getFakeForecastDay() = ForecastDay(
        date = "2023-08-28",
        day = getFakeDay()
    )

    fun getFakeForecastList(): List<ForecastDay> {
        val forecastDay = getFakeForecastDay()
        return listOf(forecastDay, forecastDay)
    }

    fun getFakeCurrent() =
        Current(
            condition = getFakeCondition(),
            temperature = 40.0
        )

    fun getFakeForecast() =
        Forecast(
            current = getFakeCurrent(),
            forecastList = getFakeForecastList(),
            location = getFakeLocation()
        )
}
