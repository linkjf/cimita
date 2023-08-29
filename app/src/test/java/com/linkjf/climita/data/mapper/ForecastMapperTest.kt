package com.linkjf.climita.data.mapper

import com.linkjf.climita.remote.fakes.FakeRemoteData
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn

@RunWith(MockitoJUnitRunner::class)
class ForecastMapperTest {

    @Mock
    lateinit var currentMapper: CurrentMapper

    @Mock
    lateinit var forecastDayMapper: ForecastDayMapper

    @Mock
    lateinit var locationMapper: LocationMapper

    private lateinit var mapper: ForecastMapper

    @Before
    fun setUp() {
        mapper =
            ForecastMapper(
                currentMapper,
                forecastDayMapper,
                locationMapper
            )
    }

    @Test
    fun `given a ForecastEntity when mapFromEntity should return Forecast`() =
        run {

            // Given
            val responseModel = FakeRemoteData.getFakeForecastEntity()
            Mockito.`when`(currentMapper.mapFromEntity(any())) doReturn FakeRemoteData.getFakeCurrent()
            Mockito.`when`(forecastDayMapper.mapFromEntity(any())) doReturn FakeRemoteData.getFakeForecastList()
            Mockito.`when`(locationMapper.mapFromEntity(any())) doReturn FakeRemoteData.getFakeLocation()

            // When
            val result = mapper.mapFromEntity(responseModel)

            // Should
            TestCase.assertEquals(result.forecastList.size, 2)
            TestCase.assertEquals(result.forecastList.size, 2)
        }

    @Test
    fun `given a Forecast when mapToEntity should return ForecastEntity`() =
        run {

            // Given
            val responseModel = FakeRemoteData.getFakeForecast()
            Mockito.`when`(currentMapper.mapToEntity(any())) doReturn FakeRemoteData.getFakeCurrentEntity()
            Mockito.`when`(forecastDayMapper.mapToEntity(any())) doReturn FakeRemoteData.getFakeForecastEntityList()
            Mockito.`when`(locationMapper.mapToEntity(any())) doReturn FakeRemoteData.getFakeLocationEntity()

            // When
            val result = mapper.mapToEntity(responseModel)

            // Should
            TestCase.assertEquals(result.forecastList.size, 2)
            TestCase.assertEquals(result.forecastList.size, 2)
        }
}
