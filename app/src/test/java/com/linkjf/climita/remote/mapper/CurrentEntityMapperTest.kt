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
class CurrentEntityMapperTest {

    @Mock
    lateinit var conditionEntityMapper: ConditionEntityMapper

    private lateinit var mapper: CurrentEntityMapper

    @Before
    fun setUp() {
        mapper = CurrentEntityMapper(
            conditionEntityMapper
        )
    }

    @Test
    fun `given a CurrentResponse when mapFromModel should return CurrentEntity `() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeCurrentResponse()
            `when`(conditionEntityMapper.mapFromModel(any())) doReturn FakeRemoteData.getFakeConditionEntity()

            // When
            val result = mapper.mapFromModel(responseModel)

            // Should
            TestCase.assertEquals(result.temperature, 40.0)
            TestCase.assertEquals(result.condition.icon, "icon")
            TestCase.assertEquals(result.condition.text, "Condition")
        }
}
