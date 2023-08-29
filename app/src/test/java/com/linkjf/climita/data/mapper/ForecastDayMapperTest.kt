package com.linkjf.climita.data.mapper

import com.linkjf.climita.remote.fakes.FakeRemoteData
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn

@RunWith(MockitoJUnitRunner::class)
class ForecastDayMapperTest {

    @Mock
    lateinit var dayMapper: DayMapper

    private lateinit var mapper: ForecastDayMapper

    @Before
    fun setUp() {
        mapper = ForecastDayMapper(dayMapper)
    }

    @Test
    fun `given a list of ForecastDayEntity when mapFromEntity should return a list of ForecastDay`() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeForecastEntityList()
            Mockito.`when`(dayMapper.mapFromEntity(any())) doReturn FakeRemoteData.getFakeDay()

            // When
            val result = mapper.mapFromEntity(responseModel)

            // Should
            assertEquals(result.size, 2)
            assertEquals(result[0].date, "2023-08-28")
            assertEquals(result[1].day.maxTemperature, 50.0)
        }

    @Test
    fun `given a list of ForecastDay when mapToEntity should return a list of ForecastDayEntity`() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeForecastList()
            Mockito.`when`(dayMapper.mapToEntity(any())) doReturn FakeRemoteData.getFakeDayEntity()

            // When
            val result = mapper.mapToEntity(responseModel)

            // Should
            assertEquals(result.size, 2)
            assertEquals(result[0].date, "2023-08-28")
            assertEquals(result[1].day.maxTemperature, 50.0)
        }
}
