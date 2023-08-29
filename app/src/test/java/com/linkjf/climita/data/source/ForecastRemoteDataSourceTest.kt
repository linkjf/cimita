package com.linkjf.climita.data.source

import com.linkjf.climita.data.repository.ForecastDataSource
import com.linkjf.climita.data.repository.ForecastRemote
import com.linkjf.climita.remote.common.Result
import com.linkjf.climita.remote.fakes.FakeRemoteData
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn

@RunWith(MockitoJUnitRunner::class)
class ForecastRemoteDataSourceTest {

    @Mock
    lateinit var forecastRemote: ForecastRemote

    private lateinit var forecastRemoteDataSource: ForecastDataSource

    @Before
    fun setUp() {
        forecastRemoteDataSource = ForecastRemoteDataSource(
            forecastRemote
        )
    }

    @Test
    fun `getForecast whit a valid query should return a ForecastResponse with 2 ForecastDayResponse`() =
        runBlocking {

            // Given
            val fakeResponse = FakeRemoteData.getFakeForecastEntity()
            `when`(forecastRemote.getForecast(any(), any())) doReturn Result.Success(
                fakeResponse
            )

            //When
            val result = forecastRemoteDataSource.getForecast(
                FakeRemoteData.fakeQuery,
                FakeRemoteData.fakeDays
            )

            // Then
            assertThat(result, instanceOf(Result.Success::class.java))
            assertEquals((result as Result.Success).data.forecastList.size, 2)
        }
}
