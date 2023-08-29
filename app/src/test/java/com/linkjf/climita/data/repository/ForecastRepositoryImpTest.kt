package com.linkjf.climita.data.repository

import com.linkjf.climita.data.mapper.ForecastMapper
import com.linkjf.climita.data.source.ForecastRemoteDataSource
import com.linkjf.climita.domain.repository.ForecastRepository
import com.linkjf.climita.remote.common.Result.Success
import com.linkjf.climita.remote.fakes.FakeRemoteData
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert
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
class ForecastRepositoryImpTest {

    @Mock
    lateinit var forecastRemoteDataSource: ForecastRemoteDataSource

    @Mock
    lateinit var forecastMapper: ForecastMapper

    private lateinit var forecastRepository: ForecastRepository

    @Before
    fun setUp() {
        forecastRepository = ForecastRepositoryImp(
            forecastRemoteDataSource,
            forecastMapper
        )
    }

    @Test
    fun `getForecast whit a valid query should return a Forecast with 2 ForecastDay`() =
        runBlocking {

            val fakeResponse = FakeRemoteData.getFakeForecastEntity()
            val fakeModel = FakeRemoteData.getFakeForecast()
            `when`(
                forecastRemoteDataSource.getForecast(
                    any(),
                    any()
                )
            ) doReturn Success(fakeResponse)
            `when`(forecastMapper.mapFromEntity(any())) doReturn fakeModel

            //When
            val result = forecastRepository.getForecast(
                FakeRemoteData.fakeQuery,
                FakeRemoteData.fakeDays
            ).single()

            // Then
            assertThat(result, instanceOf(Success::class.java))
            assertEquals((result as Success).data.forecastList.size, 2)
        }
}
