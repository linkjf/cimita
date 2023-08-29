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
class CurrentMapperTest {

    @Mock
    lateinit var conditionMapper: ConditionMapper

    private lateinit var mapper: CurrentMapper

    @Before
    fun setUp() {
        mapper = CurrentMapper(
            conditionMapper
        )
    }

    @Test
    fun `given a CurrentEntity when mapFromEntity should return Current`() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeCurrentEntity()
            Mockito.`when`(conditionMapper.mapFromEntity(any())) doReturn FakeRemoteData.getFakeCondition()

            // When
            val result = mapper.mapFromEntity(responseModel)

            // Should
            TestCase.assertEquals(result.temperature, 40.0)
            TestCase.assertEquals(result.condition.icon, "icon")
            TestCase.assertEquals(result.condition.text, "Condition")
        }

    @Test
    fun `given a Current when mapToEntity should return CurrentEntity`() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeCurrent()
            Mockito.`when`(conditionMapper.mapToEntity(any())) doReturn FakeRemoteData.getFakeConditionEntity()

            // When
            val result = mapper.mapToEntity(responseModel)

            // Should
            TestCase.assertEquals(result.temperature, 40.0)
            TestCase.assertEquals(result.condition.icon, "icon")
            TestCase.assertEquals(result.condition.text, "Condition")
        }
}
