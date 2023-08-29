package com.linkjf.climita.remote.mapper

import com.linkjf.climita.remote.fakes.FakeRemoteData
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn

@RunWith(MockitoJUnitRunner::class)
class ForecastEntityMapperTest {

    @Mock
    lateinit var currentEntityMapper: CurrentEntityMapper

    @Mock
    lateinit var forecastDayEntityMapper: ForecastDayEntityMapper

    @Mock
    lateinit var locationEntityMapper: LocationEntityMapper

    private lateinit var mapper: ForecastEntityMapper

    @Before
    fun setUp() {
        mapper =
            ForecastEntityMapper(
                currentEntityMapper,
                forecastDayEntityMapper,
                locationEntityMapper
            )
    }

    @Test
    fun `given a ForecastResponse when mapFromModel should return ForecastEntity`() = run { // Given
        val responseModel = FakeRemoteData.getFakeForecastResponse()
        `when` (currentEntityMapper.mapFromModel(any())) doReturn FakeRemoteData.getFakeCurrentEntity()
        `when` (forecastDayEntityMapper.mapFromModel(any())) doReturn FakeRemoteData.getFakeForecastEntityList()
        `when` (locationEntityMapper.mapFromModel(any())) doReturn FakeRemoteData.getFakeLocationEntity()

        // When
        val result = mapper.mapFromModel(responseModel)

        // Should
        assertEquals(result.forecastList.size, 2)
        assertEquals(result.forecastList.size, 2)
    }
}
