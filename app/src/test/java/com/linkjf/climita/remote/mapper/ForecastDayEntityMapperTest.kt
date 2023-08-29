package com.linkjf.climita.remote.mapper

import com.linkjf.climita.remote.fakes.FakeRemoteData
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn

@RunWith(MockitoJUnitRunner::class)
class ForecastDayEntityMapperTest {

    @Mock
    lateinit var dayEntityMapper: DayEntityMapper

    private lateinit var mapper: ForecastDayEntityMapper

    @Before
    fun setUp() {
        mapper = ForecastDayEntityMapper(dayEntityMapper)
    }

    @Test
    fun `given a ForecastListResponse when mapFromModel should return a list of ForecastDayEntity `() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeForecastListResponse()
            `when`(dayEntityMapper.mapFromModel(any())) doReturn FakeRemoteData.getFakeDayEntity()

            // When
            val result = mapper.mapFromModel(responseModel)

            // Should
            TestCase.assertEquals(result.size, 2)
            TestCase.assertEquals(result[0].date, "2023-08-28")
            TestCase.assertEquals(result[1].day.maxTemperature, 50.0)
        }
}
