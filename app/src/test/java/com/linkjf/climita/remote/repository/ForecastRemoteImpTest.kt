package com.linkjf.climita.remote.repository

import com.linkjf.climita.data.repository.ForecastRemote
import com.linkjf.climita.remote.api.ForecastService
import com.linkjf.climita.remote.common.Result.Error
import com.linkjf.climita.remote.common.Result.Success
import com.linkjf.climita.remote.fakes.FakeRemoteData
import com.linkjf.climita.remote.fakes.FakeRemoteData.fakeDays
import com.linkjf.climita.remote.fakes.FakeRemoteData.fakeQuery
import com.linkjf.climita.remote.mapper.ForecastEntityMapper
import com.linkjf.climita.remote.models.ForecastResponse
import junit.framework.TestCase.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import retrofit2.Response
import retrofit2.Response.success

@RunWith(MockitoJUnitRunner::class)
class ForecastRemoteImpTest {

    @Mock
    lateinit var forecastService: ForecastService

    @Mock
    lateinit var forecastEntityMapper: ForecastEntityMapper

    @Mock
    lateinit var responseError: Response<ForecastResponse>

    private lateinit var forecastRemote: ForecastRemote

    @Before
    fun setUp() {
        forecastRemote = ForecastRemoteImp(
            forecastService,
            forecastEntityMapper
        )
    }

    @Test
    fun `getForecast whit a valid query should return a ForecastResponse with 2 ForecastDayResponse`() =
        runBlocking {

            // Given
            val fakeResponse = FakeRemoteData.getFakeForecastResponse()
            val fakeEntity = FakeRemoteData.getFakeForecastEntity()
            `when`(forecastService.getForecast(any(), any())) doReturn success(fakeResponse)
            `when`(forecastEntityMapper.mapFromModel(any())) doReturn fakeEntity

            //When
            val result = forecastRemote.getForecast(fakeQuery, fakeDays)

            // Then
            assertThat(result, instanceOf(Success::class.java))
            assertEquals((result as Success).data.forecastList.size, 2)
        }

    @Test
    fun `getForecast whit a invalid query should return a Error response`() =
        runBlocking {

            // Given
            `when`(forecastService.getForecast(any(), any())) doReturn responseError

            //When
            val result = forecastRemote.getForecast(fakeQuery, fakeDays)

            // Then
            assertThat(result, instanceOf(Error::class.java))
            assertEquals((result as Error).code, 0)
        }
}
