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
class DayMapperTest {

    @Mock
    lateinit var conditionMapper: ConditionMapper

    private lateinit var mapper: DayMapper

    @Before
    fun setUp() {
        mapper = DayMapper(
            conditionMapper
        )
    }

    @Test
    fun `given a DayEntity when mapFromEntity should return Day`() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeDayEntity()
            Mockito.`when`(conditionMapper.mapFromEntity(any())) doReturn FakeRemoteData.getFakeCondition()

            // When
            val result = mapper.mapFromEntity(responseModel)

            // Should
            assertEquals(result.condition.code, 1)
            assertEquals(result.maxTemperature, 50.0)
            assertEquals(result.averageTemperature, 40.0)
        }

    @Test
    fun `given a Day when mapToEntity should return DayEntity`() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeDay()
            Mockito.`when`(conditionMapper.mapToEntity(any())) doReturn FakeRemoteData.getFakeConditionEntity()

            // When
            val result = mapper.mapToEntity(responseModel)

            // Should
            assertEquals(result.condition.code, 1)
            assertEquals(result.maxTemperature, 50.0)
            assertEquals(result.averageTemperature, 40.0)
        }

}
