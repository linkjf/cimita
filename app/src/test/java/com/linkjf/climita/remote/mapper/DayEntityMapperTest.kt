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
class DayEntityMapperTest {

    @Mock
    lateinit var conditionEntityMapper: ConditionEntityMapper

    private lateinit var mapper: DayEntityMapper

    @Before
    fun setUp() {
        mapper = DayEntityMapper(conditionEntityMapper)
    }

    @Test
    fun `given a DayResponse when mapFromModel should return DayEntity `() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeDayResponse()
            `when`(conditionEntityMapper.mapFromModel(any())) doReturn FakeRemoteData.getFakeConditionEntity()

            // When
            val result = mapper.mapFromModel(responseModel)

            // Should
            TestCase.assertEquals(result.condition.code, 1)
            TestCase.assertEquals(result.maxTemperature, 50.0)
            TestCase.assertEquals(result.averageTemperature, 40.0)
        }
}
