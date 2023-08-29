package com.linkjf.climita.data.mapper

import com.linkjf.climita.remote.fakes.FakeRemoteData
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ConditionMapperTest {
    private lateinit var mapper: ConditionMapper

    @Before
    fun setUp() {
        mapper = ConditionMapper()
    }

    @Test
    fun `given a ConditionEntity when mapFromEntity should return Condition`() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeConditionEntity()

            // When
            val result = mapper.mapFromEntity(responseModel)

            // Should
            assertEquals(result.code, 1)
            assertEquals(result.icon, "icon")
            assertEquals(result.text, "Condition")
        }

    @Test
    fun `given a Condition when mapToEntity should return ConditionEntity`() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeCondition()

            // When
            val result = mapper.mapToEntity(responseModel)

            // Should
            assertEquals(result.code, 1)
            assertEquals(result.icon, "icon")
            assertEquals(result.text, "Condition")
        }
}
